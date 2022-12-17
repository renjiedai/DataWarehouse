package cn.edu.tongji.dwbackend.neo4j.service.impl;

import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.repository.noderepository.MovieRepository;
import com.example.neo4jtest.service.nodeservice.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie findByName(String name) {
        return movieRepository.findByName(name);
    }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }
}
