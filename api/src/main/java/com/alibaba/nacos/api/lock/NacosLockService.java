package com.alibaba.nacos.api.lock;

import com.alibaba.nacos.api.lock.model.LockInfo;

/**
 * @author 985492783@qq.com
 * @date 2023/6/28 2:47
 */
public interface NacosLockService {

    
    Boolean lock(LockInfo lockInfo);
    
    
}
