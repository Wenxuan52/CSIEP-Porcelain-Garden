package com.briup.shop.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author adam
 */
@Entity
@Data
@Table(name = "t_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId;

}
