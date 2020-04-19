package ru.otus.source;

import java.util.List;

public interface Dao {
    int create(String query);

    int create(String query, List params);

    int insert(String query);

    int insert(String query, List params);

    int select(String query);

    int select(String query, List params);

    int update(String query);

    int update(String query, List params);

    int delete(String query);

    int delete(String query, List params);

    <T> T selectObjectById(long id, Class<T> clazz);


}
