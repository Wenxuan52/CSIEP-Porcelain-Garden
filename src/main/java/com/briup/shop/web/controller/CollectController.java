package com.briup.shop.web.controller;

import com.briup.shop.bean.Collect;
import com.briup.shop.bean.User;
import com.briup.shop.service.ICollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author adam
 * @date 2022/1/15
 */
@Controller
@Slf4j
public class CollectController {
    @Autowired
    private ICollectService collectService;

    @GetMapping("toCollect")
    public String toCollect(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<Collect> collects = collectService.findUserAllCollect(user.getId());
        model.addAttribute("collects",collects);
        return "collect";
    }
    @ResponseBody
    @GetMapping("addCollect")
    public void  addCollect(Long shopId,HttpSession session){
        User user = (User) session.getAttribute("user");
        log.info("{},{},addCollect,{}", user.getId(), shopId, System.currentTimeMillis());

        collectService.addCollect(user,shopId);
    }
    @ResponseBody
    @GetMapping("delCollect")
    public void  delCollect(Long collectId){
       Collect collect= collectService.findOne(collectId);
        log.info("{},{},delCollect,{}", collect.getUser().getId(), collect.getShop().getId(), System.currentTimeMillis());

        collectService.deleteCollect(collectId);
    }

    @ResponseBody
    @GetMapping("findCollect")
    public boolean  findCollect(Long shopId, HttpSession session, HttpServletResponse response){
        User user = (User) session.getAttribute("user");
        boolean b=collectService.findCollect(user.getId(),shopId);
        return  b;
    }
}
