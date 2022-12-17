package cn.edu.tongji.dwbackend.neo4j.repository;

import com.example.neo4jtest.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie,String> {
    //we must define the function in the repository manually
    Movie findByName(String name);
    //Movie findOneByMovie_id(Long movie_id);
}
