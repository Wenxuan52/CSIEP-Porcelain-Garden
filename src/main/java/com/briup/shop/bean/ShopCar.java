package com.briup.shop.bean;

import lombok.Data;

import javax.persistence.*;

/**
 * @author adam
 * @date 2022/1/11
 */
@Data
@Entity
@Table(name = "t_shop_car")
public class ShopCar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private int num;
    @OneToOne
    private Shop shop;
    @ManyToOne
    private User user;

}
