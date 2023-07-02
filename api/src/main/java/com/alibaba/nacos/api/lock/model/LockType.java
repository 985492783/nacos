package com.alibaba.nacos.api.lock.model;

import java.io.Serializable;

/**
 * @author qiyue.zhang@aloudata.com
 * @description LockType
 * @date 2023/6/29 10:20
 */
public enum LockType implements Serializable {
    /**
     * get lock once,success return true,fail return false
     */
    PRIMARY,
    /**
     * Acquire locks in a loop until lock success or times out
     */
    SPIN,
    /**
     * get lock once,success return true,fail will block until lock success or times out
     */
    BLOCK
    
}
