package com.briup.shop.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author adam
 * @date 2022/1/11
 */
@Data
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date createDate;

    private int shopNum;
    private BigDecimal sumPrice;
    /**
     * 1. 未支付
     * 2. 已支付 待发货
     * 3. 已发货 待签收
     * 4. 已完成
     */
    private int status;
    @ManyToOne
    private User user;
    @ManyToOne
    private ShippingAddress shippingAddress;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    {
        sumPrice = new BigDecimal("0");
        this.id = "briup" + UUID.randomUUID();
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        orderItems.stream().map(i -> i.getNum()).forEach(i -> shopNum += i);
        System.out.println(this.sumPrice);
        orderItems.stream().map(i -> i.getShop().isDiscount() ? i.getShop().getDiscountPrice().multiply(new BigDecimal(i.getNum())) : i.getShop().getSelling_price().multiply(new BigDecimal(i.getNum()))).forEach(i -> sumPrice = sumPrice.add(i));
    }

    public Order(User user, ShippingAddress shippingAddress) {
        this.user = user;
        this.shippingAddress = shippingAddress;
        this.createDate=new Date();
    }

    public Order() {
    }
}
