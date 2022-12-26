package com.example.backend.daoimpl;

import com.example.backend.dao.TypeDao;
import com.example.backend.entity.Type;
import com.example.backend.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TypeDaoImpl implements TypeDao {
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type findByTypename(String typename) {
        return typeRepository.findByTypename(typename);
    }

    @Override
    public List<Type> findNearTypesByTypename(String typename) {
        Set<Type> result = new HashSet<>();
        Type type = typeRepository.findByTypename(typename);
//        System.out.println("type:");
//        System.out.println(type);
        if (type != null) {
            List<Type> subTypesLevel1 = new ArrayList<>(type.getSubTypes());
            for (Type t1 : subTypesLevel1) {
                result.add(t1);
                List<Type> subTypesLevel2 = new ArrayList<>(t1.getSubTypes());
                for (Type t2 : subTypesLevel2) result.add(t2);
            }
        }
        System.out.println("result:");
        System.out.println(result);
        return new ArrayList<Type>(result);
    }
}
