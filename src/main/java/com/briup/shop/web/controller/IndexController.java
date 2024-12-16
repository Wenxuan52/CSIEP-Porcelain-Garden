package com.briup.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author adam
 * @date 2022/1/14
 */
@Controller
public class IndexController {
    @GetMapping(value = {"/", "/toIndex"})
    public String toIndex() {
        return "index";
    }
    @GetMapping(value = {"/error"})
    public String toError() {
        return "error";
    }




}
