package cn.edu.tongji.dwbackend.neo4j.repository;

import com.example.neo4jtest.model.Director;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends Neo4jRepository<Director,String> {
    Director findByName(String name);
}
