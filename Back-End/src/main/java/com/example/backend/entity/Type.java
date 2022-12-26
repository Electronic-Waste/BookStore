package com.example.backend.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Node
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    private String typename;

    private Type() {
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Type(String typename) {this.typename = typename;}

    @Relationship(type = "subType")
    public Set<Type> subTypes;

    public void addSubTypes(Type type) {
        if (subTypes == null) {
            subTypes = new HashSet<>();
        }
        subTypes.add(type);
    }

    public Set<Type> getSubTypes() {return this.subTypes;}

    public String toString() {
        return this.typename + "'s subTypes=> "
                + Optional.ofNullable(this.subTypes).orElse(
                    Collections.emptySet()).stream()
                        .map(Type::getTypeName)
                        .collect(Collectors.toList());
    }

    public String getTypeName() {return this.typename;}

    public void setTypeName(String typename) {this.typename = typename;}
}
