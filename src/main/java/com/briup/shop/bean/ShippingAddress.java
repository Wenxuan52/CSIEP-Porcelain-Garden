package com.briup.shop.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author adam
 * @date 2022/1/11
 */
@Data
@Entity
@Table(name = "t_shipping_address")
@Cacheable(false)
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNum;
    private boolean defaultValue;
    @ManyToOne
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", defaultValue=" + defaultValue +
                '}';
    }

    public ShippingAddress(Long id) {
        this.id = id;
    }

    public ShippingAddress() {
    }

    public ShippingAddress(Long id, String name, String address, String phoneNum, boolean defaultValue, User user) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.defaultValue = defaultValue;
        this.user = user;
    }
}
