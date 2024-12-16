package com.briup.shop.web.controller;

import com.briup.shop.bean.Shop;
import com.briup.shop.bean.User;
import com.briup.shop.service.IShopService;
import com.briup.shop.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author adam
 * @date 2022/1/13
 */
@Controller
@Slf4j
public class UserController {
    @Autowired
    private IUserService service;
    @Autowired
    private IShopService shopService;

    @GetMapping("/toLogin")
    public  String toLogin(){
        return "login";
    }
    @GetMapping("toRegister")
    public  String toRegister(){
        return "register";
    }
    @PostMapping("login")
    public String login(String loginName, String password, Model model, HttpSession session){
        try {
            User user = service.login(loginName, password);
            session.setAttribute("user",user);
            List<Shop> recommendShops = shopService.recommendShop(user.getId());
            session.setAttribute("recommendShops",recommendShops);
            return "index";
        } catch (Exception e) {
            model.addAttribute("msg",e.getMessage());
            return "login";
        }
    }

    @PostMapping("register")
    public String register(User user,Model model){
        try {
             service.register(user);
            return "login";
        } catch (Exception e) {
            model.addAttribute("msg",e.getMessage());
            return "register";
        }
    }
    @GetMapping("exit")
    public String exit(HttpSession session){
        session.setAttribute("user",null);
        return "login";
    };



}
