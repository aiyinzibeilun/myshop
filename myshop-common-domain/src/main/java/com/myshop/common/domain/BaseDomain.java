package com.myshop.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用字段
 * @Author yang
 * @Date 2019/6/4
 */
@Getter
@Setter
public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 678343492023517449L;
    private Date created;

    private Date updated;
}
