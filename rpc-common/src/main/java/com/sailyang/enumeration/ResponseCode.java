package com.sailyang.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/9/10 20:34
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(200),
    FAIL(404);
    private final int code;
}
