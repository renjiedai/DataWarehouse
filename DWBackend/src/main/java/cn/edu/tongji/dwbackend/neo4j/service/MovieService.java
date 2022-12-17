package cn.edu.tongji.dwbackend.neo4j.service;

import com.example.neo4jtest.model.Movie;

import java.util.Collection;


public interface MovieService {
    Movie findByName(String name);

    Collection<Movie> findAll();
}
