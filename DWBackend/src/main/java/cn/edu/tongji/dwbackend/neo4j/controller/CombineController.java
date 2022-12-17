package cn.edu.tongji.dwbackend.neo4j.controller;

import org.neo4j.driver.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/combine")
public class CombineController {
    private final Driver driver = GraphDatabase
            .driver("neo4j://47.100.205.153:7687", AuthTokens.basic("neo4j", "dw1234"));

    @GetMapping("/typepair")
    public Integer findpair(@RequestParam String type){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(m:movie)-[:belong]->(n:type where n.type_name=\""+type+"\" with m " + " order by m.comment_num DESC limit 1 match(a:actor)-[:star_in]->(m) return a");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }

    @GetMapping("/findcomb1")
    public Integer findmovie1(@RequestParam String directorName,@RequestParam String type,@RequestParam String rate){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match (d:director)-[:direct_in]->(m:movie) where m.type_name=\""+type+"\" and m.neg_rat>"+rate+"and d.director_name=\""+directorName+"\" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }

    @GetMapping("/findcomb2")
    public Integer findmovie2(@RequestParam String name,@RequestParam String start,@RequestParam String end, @RequestParam String score){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match (a:actor)-[:star_in]->(m:movie) where m.score>"+score+" and a.actor_name=\""+name+"\" and m.release_time>\""+start+"\" and m.release_time<\""+end+"\" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }
}
