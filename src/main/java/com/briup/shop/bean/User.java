package com.briup.shop.bean;

import lombok.Data;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.util.List;

/**
 * @author adam
 * @date 2022/1/11
 */
@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    private String loginName;
    private String passwordMd5;
    private String phone;
    private String eMail;
    private String address;
    private  boolean isLock;
    @OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
    private List<ShippingAddress> addresses;


    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = DigestUtils.md5DigestAsHex(passwordMd5.getBytes());
    }
}
