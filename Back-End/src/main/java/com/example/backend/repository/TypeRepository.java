package com.example.backend.repository;

import com.example.backend.entity.Type;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface TypeRepository extends Neo4jRepository<Type, Long> {
    Type findByTypename(String typename);

    List<Type> findBySubTypesTypename(String typename);
}
