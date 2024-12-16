package com.briup.shop.bean;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.annotation.Generated;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author adam
 */
@Data
@Entity
@Table(name = "t_shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  name;
    //售价
    private BigDecimal selling_price;

    //进价
    private BigDecimal price;
    //商品规格
    private String  info;
    //介绍
    private String intro;
    private String img;
    private String store;
    //库存数量
    private int stockNum;
    //是否特价
    private boolean discount;
    //特价
    private BigDecimal discountPrice;
    @ManyToOne
    private Category category;
    //上架状态
    private boolean stat;

    private String tags;
    //销量
    private long salesVolume;
    //浏览量
    private long visitVolume;




}
