package cn.edu.tongji.dwbackend.neo4j.repository;

import com.example.neo4jtest.model.Actor;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface ActorRepository extends Neo4jRepository<Actor,String> {
    Actor findByName(String name);

}
