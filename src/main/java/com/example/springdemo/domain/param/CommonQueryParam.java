package com.example.springdemo.domain.param;

import com.example.springdemo.domain.BaseObject;
import lombok.Data;

@Data
public class CommonQueryParam extends BaseObject {

    private static final long serialVersionUID = -635955135108787873L;

    /**
     * 数据id
     */
    private String id;

}
