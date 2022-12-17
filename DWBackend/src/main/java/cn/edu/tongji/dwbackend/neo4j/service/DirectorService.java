package cn.edu.tongji.dwbackend.neo4j.service;

import com.example.neo4jtest.model.Director;

import java.util.List;

public interface DirectorService {
    Director findByName(String name);

    List<String> getNum(String name);
}
