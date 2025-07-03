package com.amos.silog.issues.mapper;

public interface UpdateEntity<T, K> {
    void updateEntity(T entity, K key);
}
