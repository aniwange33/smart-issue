package com.amos.silog.issue.mapper;

public interface UpdateEntity<T, K> {
    void updateEntity(T entity, K key);
}
