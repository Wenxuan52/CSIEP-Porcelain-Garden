package com.briup.shop.service.impl;

import com.briup.shop.bean.ShippingAddress;
import com.briup.shop.dao.IShippingAddressDao;
import com.briup.shop.service.IShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author adam
 * @date 2022/1/12
 */
@Service
public class ShippingAddressServiceImpl implements IShippingAddressService {
    @Autowired
    private IShippingAddressDao shippingAddressDao;
    @Override
    @Transactional
    public void saveShippingAddress(ShippingAddress shippingAddress) {
        shippingAddressDao.save(shippingAddress);
    }

    @Override
    public List<ShippingAddress> findUserAllShippingAddress(Long id) {
        List<ShippingAddress> list= shippingAddressDao.findByUserId(id);
        return list;
    }
}
