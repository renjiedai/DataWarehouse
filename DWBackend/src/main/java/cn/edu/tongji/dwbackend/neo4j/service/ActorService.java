package cn.edu.tongji.dwbackend.neo4j.service;

import com.example.neo4jtest.model.Actor;

import java.util.List;


public interface ActorService {
    Actor findByName(String name);

    List<String> getactnum(Actor a);

    List<String> getstarnum(Actor a);

    List<String> getjoinnum(Actor a);

}
