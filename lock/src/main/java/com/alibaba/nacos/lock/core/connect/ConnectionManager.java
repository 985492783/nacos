package com.alibaba.nacos.lock.core.connect;

/**
 * @author 985492783@qq.com
 * @description ConnectionManager
 * @date 2023/7/12 13:41
 */
public interface ConnectionManager {
    
    void createConnectionSync(String connectionId);
    
    void destroyConnectionSync(String connectionId);
    
    boolean isAlive(String connectionId);
}
