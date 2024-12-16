package com.briup.shop.web.controller;

import com.briup.shop.bean.ShippingAddress;
import com.briup.shop.bean.User;
import com.briup.shop.service.IShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author adam
 * @date 2022/4/1
 */
@Controller
public class ShippingAddressController {
    @Autowired
    IShippingAddressService service;

    @GetMapping("toAddShippingAddress")
    public String toAddShippingAddress(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        List<ShippingAddress> list = service.findUserAllShippingAddress(user.getId());
        model.addAttribute("addressList",list);
        user.setAddresses(list);
        //model.addAttribute("user",user);
        return "addShippingAddress";
    }
    @PostMapping("addShippingAddress")
    public void  addShippingAddress(ShippingAddress shippingAddress, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) session.getAttribute("user");
        shippingAddress.setUser(user);
        service.saveShippingAddress(shippingAddress);
       response.sendRedirect("toAddShippingAddress");
    }
}
