package com.briup.shop.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author adam
 * @date 2022/1/11
 */
@Data
@Entity
@Table(name = "t_order_item")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Shop shop;
    private int num;
    @ManyToOne
    @JsonIgnore
    private Order order;
    public  OrderItem(ShopCar shopCar){
        this.shop=shopCar.getShop();
        this.num=shopCar.getNum();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", shop=" + shop +
                ", num=" + num +
                '}';
    }
}
