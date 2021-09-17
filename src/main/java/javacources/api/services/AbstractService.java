package javacources.api.services;

import javacources.entitiy.BaseEntity;
import javacources.entitiy.Room;
import javacources.entitiy.Services;

import java.util.List;

public interface AbstractService<T extends BaseEntity> {
    void add(T entity);

    T get(int entityId);

    List<T> getAll();

    void update(T entity);
}
