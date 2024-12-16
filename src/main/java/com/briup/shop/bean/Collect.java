package com.briup.shop.bean;

import lombok.Data;

import javax.persistence.*;

/**
 * @author adam
 * @date 2022/1/11
 */
@Entity
@Data
@Table(name = "t_collect")
public class Collect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Shop shop;
}
