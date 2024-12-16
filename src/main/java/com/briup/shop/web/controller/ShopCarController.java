package com.briup.shop.web.controller;

import com.briup.shop.bean.ShopCar;
import com.briup.shop.bean.User;
import com.briup.shop.service.IShopCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author adam
 * @date 2022/1/15
 */
@Controller
@Slf4j
public class ShopCarController {
    @Autowired
    private IShopCarService shopCarService;

    @GetMapping("addShopCar")
    @ResponseBody
    public void addShopCar(Long shopId, HttpSession session){
        User user = (User) session.getAttribute("user");
        log.info("{},{},addCart,{}",user.getId(),shopId,System.currentTimeMillis());
        shopCarService.saveShopCar(user.getId(),shopId);

    }
    @GetMapping("toShopCar")
    public String toShopCar(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<ShopCar> shopCarList = shopCarService.findUserAllShopCar(user.getId());
        model.addAttribute("shopCarList",shopCarList);
        return "shopCar";
    }
    @GetMapping("delShopCar")
    @ResponseBody
    public void delShopCar(Long shopCarId){
        shopCarService.deleteShopCar(shopCarId);
    }
    @GetMapping("updateShopCar")
    @ResponseBody
    public void updateShopCar(Long shopCarId,int num){
        shopCarService.updateShopCar(shopCarId,num);
    }
}
