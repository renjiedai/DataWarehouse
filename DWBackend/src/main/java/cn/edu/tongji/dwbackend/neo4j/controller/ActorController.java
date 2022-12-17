package cn.edu.tongji.dwbackend.neo4j.controller;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/actor")
public class ActorController {
    private final Driver driver = GraphDatabase
            .driver("neo4j://47.100.205.153:7687", AuthTokens.basic("neo4j", "dw1234"));
//    private final Driver driver;
//    public ActorController(Driver d){driver=d;}

    /*
    @GetMapping("/findmosttwo")
    public HashMap<String, Object> findMostTwo(){
        Session session=driver.session();
        Result res=session.run("match(p1:actor)-[:act_in]->(m:movie)<-[:act_in]-(p2:actor) " +
                "return p1.actor_name as a1,p2.actor_name as a2, count(*) AS time " +
                "ORDER BY time DESC");
        List<Record> relation=res.list();
        HashMap<String,Object> response=new HashMap<>();
        response.put("number",relation.get(0).get(2).toString());
        response.put("actor2",relation.get(0).get(1).asString());
        response.put("actor1",relation.get(0).get(0).asString());
        return response;
    }
     */

    //主演
    @GetMapping("/starmovie")
    public Integer findstarmovie(@RequestParam String actorName){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(p:actor)-[:star_in]->(m:movie) where p.name=\""+actorName+"\" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }

    //非主演
    @GetMapping("/actmovie")
    public Integer findactmovie(@RequestParam String actorName){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(p:actor)-[:act_in]->(m:movie) where p.name=\""+actorName+"\" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }

    //参演
    @GetMapping("/inmovie")
    public Integer findinmovie(@RequestParam String actorName){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(p:actor)-[:act_in|star_in]->(m:movie) where p.name=\""+actorName+"\" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }


    @GetMapping("/findtwo")
    public Integer findtwo(){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(p1:actor)-[:act_in|star_in]->(m:movie)<-[:act_in|star_in]-(p2:actor) " +
                "return p1.actor_name as a1,p2.actor_name as a2, count(*) AS time " +
                "ORDER BY time DESC");
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
        return ret;
         */
        return (int)(endTime-startTime);
    }

    @GetMapping("/finddir")
    public Integer finddir(){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(p1:director)-[:direct_in]->(m:movie)<-[:act_in|star_in]-(p2:actor) " +
                "return p1.director_name as a1,p2.actor_name as a2, count(*) AS time " +
                "ORDER BY time DESC ");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        /*
        List<Record> relation=res.list();
        List<Pair> pairs=new ArrayList<>();
        for(int i=0;i<10;i++){ //这里暂设输出100个结果
            if(i>=relation.size()) break;
            Pair ap=new Pair(relation.get(i).get(0).asString(),relation.get(i).get(1).asString(),relation.get(i).get(2).toString());
            pairs.add(i,ap);
        }
        HashMap<String,Object> ret=new HashMap<>();
        ret.put("data",pairs);
        ret.put("time",endTime-startTime);
        return ret;
         */
        return (int)(endTime-startTime);
    }

}
