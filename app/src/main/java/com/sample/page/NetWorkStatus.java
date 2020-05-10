package com.sample.page;

import androidx.annotation.IntDef;

/**
 * 数据加载状态
 */
public enum NetWorkStatus {
    LOADING(0),//加载中
    SUCCESS(1),//成功
    FAIL(2),//失败
    ;
    int status;

    NetWorkStatus(int status) {
        this.status = status;
    }
}