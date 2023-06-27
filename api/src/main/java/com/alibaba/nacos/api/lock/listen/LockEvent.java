package com.alibaba.nacos.api.lock.listen;

import com.alibaba.nacos.api.lock.model.LockInfo;

import java.io.Serializable;

/**
 * @author 985492783@qq.com
 * @date 2023/6/28 3:08
 */
public class LockEvent implements Serializable {
    private String key;
    
    private Boolean lock;
}
