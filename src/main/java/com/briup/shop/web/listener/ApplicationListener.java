package com.briup.shop.web.listener;

import com.briup.shop.bean.Shop;
import com.briup.shop.bean.User;
import com.briup.shop.bean.vo.CategoryVO;
import com.briup.shop.service.ICategoryService;
import com.briup.shop.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author adam
 * @date 2022/1/13
 */
@Component
public class ApplicationListener implements ServletContextListener {
    @Autowired
    private IShopService shopService;
    @Autowired
    private ICategoryService categoryService;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<Shop> shops = shopService.findAllShops();
        ServletContext application = sce.getServletContext();
        List<CategoryVO> categoryVOList = categoryService.findAllCategoey();
        application.setAttribute("shops",shops);
        application.setAttribute("categories",categoryVOList);
        List<Shop> discountList = shopService.findDiscount();
        application.setAttribute("discountList",discountList);

    }
}
