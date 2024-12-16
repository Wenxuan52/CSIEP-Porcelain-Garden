package com.briup.shop.bean;

import lombok.Data;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author adam
 * @date 2022/1/13
 */
@Data
@Entity
@Table(name = "t_recommend_shop")
public class RecommendShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private Shop shops;
    private Double recommandValue;

}
