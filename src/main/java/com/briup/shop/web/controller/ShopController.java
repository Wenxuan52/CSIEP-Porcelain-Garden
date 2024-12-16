package com.briup.shop.web.controller;

import com.briup.shop.bean.Shop;
import com.briup.shop.bean.User;
import com.briup.shop.service.IShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author adam
 * @date 2022/1/14
 */
@Controller
@Slf4j
public class ShopController {
    @Autowired
    private IShopService shopService;


    @GetMapping("toViewShop")
    public String viewShop(Long shopId, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        log.info("{},{},showProduct,{}",user.getId(),shopId,System.currentTimeMillis());
        Shop shop = shopService.findShopById(shopId);
        model.addAttribute("shop",shop);
        return "viewShop";
    }
    @GetMapping(value = "toList")
    public String toList(Long categoryId, Model model){
        List<Shop> list = shopService.findByCategory(categoryId);
        model.addAttribute("shopList",list);
        return "list";
    }
    @GetMapping("searchShop")
    public String searchShop(@RequestParam(defaultValue = "",required = true) String shopName, Model model){
       List<Shop> shopList=shopService.searchShop(shopName);
        model.addAttribute("shopList",shopList);
        return "list";
    }

}
