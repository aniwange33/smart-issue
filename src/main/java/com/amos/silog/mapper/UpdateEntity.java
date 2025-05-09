package com.amos.silog.mapper;

public interface UpdateEntity<T, K> {
    void updateEntity(T entity, K key);
}
