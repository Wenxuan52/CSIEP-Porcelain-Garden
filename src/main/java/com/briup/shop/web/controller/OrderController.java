package com.briup.shop.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.briup.shop.bean.*;
import com.briup.shop.conf.AliPayConfig;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.briup.shop.service.IOrderService;
import com.briup.shop.service.IShopCarService;
import com.briup.shop.service.IShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author adam
 * @date 2022/1/15
 */
@Controller
@Slf4j
public class OrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IShopCarService shopCarService;
    @Autowired
    IShopService shopService;

    @GetMapping("toOrder")
    public String toOrder(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.findUserAllOrders(user.getId());
        model.addAttribute("orderList", orders);
        return "order";
    }


    @GetMapping("advanceOrder")
    public String advanceOrder(Long[] ids, Model model, HttpSession session) {
        List<ShopCar> shopCars = shopCarService.findShopCars(ids);
        model.addAttribute("sumPrice", getSumPrice(shopCars));
        model.addAttribute("shopCarList", shopCars);
        User user = (User) session.getAttribute("user");

        List<ShippingAddress> addresses = user.getAddresses();
        System.out.println(addresses);
        addresses.sort((x, y) -> (y.isDefaultValue() ? 1 : 0) - (x.isDefaultValue() ? 1 : 0));
        model.addAttribute("userAddressList", addresses);
        return "confirm";
    }


    @GetMapping("createOrder")
    @ResponseBody
    public void createOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session, Long[] ids, Long addressId) throws ServletException, IOException {
        User user = (User) session.getAttribute("user");
        Order order = orderService.saveOrder(ids, user, addressId);
        for (OrderItem orderItem : order.getOrderItems()) {
            log.info("{},{},createOrder,{}", user.getId(), orderItem.getShop().getId(), System.currentTimeMillis());
        }
        request.setAttribute("order", order);
        request.getRequestDispatcher("payOrder").forward(request, response);

    }

    @GetMapping("toPayOrder")
    public void toPayOrder(HttpServletRequest request, HttpServletResponse response,String orderId) throws ServletException, IOException {
       Order order= orderService.findById(orderId);
       request.setAttribute("order",order);
        request.getRequestDispatcher("payOrder").forward(request, response);

    }
    @GetMapping("payOrder")
    @ResponseBody
    public void payOrder(HttpServletRequest request, HttpServletResponse response) {

        Order order = (Order) request.getAttribute("order");
        String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/paySuccess?id="+order.getId();
        AlipayClient alipayClient = AliPayConfig.getAlipayClient();
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        //订单支付
        model.setBody("商品描述：");
        //订单名字
        model.setSubject("杰普购物订单");
        //订单号
        model.setOutTradeNo(order.getId());
        //过期时间
        model.setTimeoutExpress("30m");
        //订单金额
        model.setTotalAmount(order.getSumPrice().toString());
        //产品码
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        //设置参数
        alipayTradePagePayRequest.setBizModel(model);
        //回调地址
        try {
            alipayTradePagePayRequest.setReturnUrl(path);
            //客户端执行,拿到支付结果
            AlipayTradePagePayResponse pageExecute = alipayClient.pageExecute(alipayTradePagePayRequest);
            String result = pageExecute.getBody();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(result);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException | IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("paySuccess")
    public String paySuccess(@RequestParam(name = "id") String orderId, Model model, HttpSession session){
       User user = (User) session.getAttribute("user");
        Order order = orderService.paySuccess(orderId);
        model.addAttribute("order",order);
        for (OrderItem orderItem : order.getOrderItems()) {
            log.info("{},{},paySuccess,{}", user.getId(), orderItem.getShop().getId(), System.currentTimeMillis());
        }
        return "confirmSuc";

    }

    BigDecimal getSumPrice(List<ShopCar> shopCars) {
        BigDecimal sumPrice = new BigDecimal("0");
        for (ShopCar shopCar : shopCars) {
            Shop shop = shopCar.getShop();
            if (shop.isDiscount()) {
                sumPrice = sumPrice.add(shop.getDiscountPrice().multiply(new BigDecimal(shopCar.getNum())));
            } else {
                sumPrice = sumPrice.add(shop.getSelling_price().multiply(new BigDecimal(shopCar.getNum())));
            }
        }
        return sumPrice;
    }
}
