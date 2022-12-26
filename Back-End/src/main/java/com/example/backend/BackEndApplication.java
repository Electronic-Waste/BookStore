package com.example.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.backend.entity.Type;
import com.example.backend.repository.TypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableNeo4jRepositories
public class BackEndApplication {

    private final static Logger log = LoggerFactory.getLogger(BackEndApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

    @Bean
    CommandLineRunner init(TypeRepository typeRepository) {
        return args -> {
            typeRepository.deleteAll();

            Type book = new Type("书");
            Type toolBook = new Type("工具书");
            Type codingBook = new Type("编程");
            Type studyBook = new Type("中小学教辅");

            List<Type> typeList = Arrays.asList(book, toolBook, codingBook, studyBook);
            log.info("Before linking up with Neo4j...");
            typeList.stream().forEach(type -> log.info("\t" + type.toString()));

            typeRepository.save(book);
            typeRepository.save(toolBook);
            typeRepository.save(codingBook);
            typeRepository.save(studyBook);

            /* book has one subType: toolBook */
            book = typeRepository.findByTypename(book.getTypeName());
            book.addSubTypes(toolBook);
            typeRepository.save(book);

            /* toolBook has two subTypes: codingBook & studyBook */
            toolBook = typeRepository.findByTypename(toolBook.getTypeName());
            toolBook.addSubTypes(codingBook);
            toolBook.addSubTypes(studyBook);
            typeRepository.save(toolBook);

            /* Verify the input */
            List<Type> subTypes = typeRepository.findBySubTypesTypename(codingBook.getTypeName());
            log.info("The following have coding book as a subType");
            subTypes.stream().forEach(type -> log.info("\t" + type.getTypeName()));

        };
    }
}
