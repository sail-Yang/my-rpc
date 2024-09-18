package com.sailyang.rpc.api;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/9/10 20:21
 */
@Data
@AllArgsConstructor
public class HelloObject implements Serializable {
    private Integer id;

    private String message;
}
