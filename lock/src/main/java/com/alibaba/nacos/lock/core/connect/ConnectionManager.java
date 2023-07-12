package com.alibaba.nacos.lock.core.connect;

/**
 * @author zhanghong
 * @description ConnectionManager
 * @date 2023/7/12 13:41
 */
public interface ConnectionManager {
    
    void createConnectionSync(String connectionId);
    
    void destroyConnectionSync(String connectionId);
    
    boolean isAlive(String connectionId);
}
