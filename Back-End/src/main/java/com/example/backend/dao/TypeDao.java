package com.example.backend.dao;

import com.example.backend.entity.Type;

import java.util.List;

public interface TypeDao {
    Type findByTypename(String typename);

    List<Type> findNearTypesByTypename(String typename);
}
