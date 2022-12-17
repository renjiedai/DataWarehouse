package cn.edu.tongji.dwbackend.neo4j.controller;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/director")
public class DirectorController {
//    private final Driver driver = GraphDatabase
//            .driver("neo4j://localhost:7687", AuthTokens.basic("neo4j", "123456"));
//    public DirectorController(Driver d){driver=d;}
    private final Driver driver = GraphDatabase
            .driver("neo4j://47.100.205.153:7687", AuthTokens.basic("neo4j", "dw1234"));

    @GetMapping("/dirmovie")
    public int findmovie(@RequestParam String directorName){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(p:director)-[:direct_in]->(m:movie) where p.name=\""+directorName+"\" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }

    @GetMapping("/findtwo")
    public Integer findtwo(){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(p1:director)-[:direct_in]->(m:movie)<-[:direct_in]-(p2:director) "+
                                    "return p1.director_name as a1,p2.director_name as a2, count(*) AS time "+
                                    "ORDER BY time DESC" );
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        /*
        List<Record> relation=res.list();
        List<Pair> pairs=new ArrayList<>();

        int index=0,i=0;
        while(i<relation.size()){
            System.out.println("-------------------");
            System.out.println(i);
            Pair ap=new Pair(relation.get(i).get(0).asString(),relation.get(i).get(1).asString(),relation.get(i).get(2).toString());
            Pair ap2=new Pair(relation.get(i).get(1).asString(),relation.get(i).get(0).asString(),relation.get(i).get(2).toString());
            if(!pairs.contains(ap2))
                pairs.add(index++,ap);
            i++;
            if(index==10) break;
        }

        HashMap<String,Object> ret=new HashMap<>();
        ret.put("data",pairs);
        ret.put("time",endTime-startTime);

         */
        /*
        System.out.println("---------------------");
        System.out.println(endTime-startTime);
        System.out.println("---------------------");
         */
        return (int)(endTime-startTime);
    }
}
