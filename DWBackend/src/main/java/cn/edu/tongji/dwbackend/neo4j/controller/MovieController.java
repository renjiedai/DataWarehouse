package cn.edu.tongji.dwbackend.neo4j.controller;

import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.service.nodeservice.MovieService;
import org.neo4j.driver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final Driver driver = GraphDatabase
            .driver("neo4j://47.100.205.153:7687", AuthTokens.basic("neo4j", "dw1234"));
//    private final Driver driver;
//    public MovieController(Driver d){driver=d;}

    @Autowired
    private MovieService movieService;

    @GetMapping("/findall")
    Collection<Movie> findAll(){
        return movieService.findAll();
    }


    @GetMapping("/byname")
    Integer getMovies(@RequestParam String name){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(m:movie) where m.movie_name contains "+name+" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }

    //score more than
    @GetMapping("/score")
    Integer byscore(@RequestParam Integer value){
        Session session=driver.session();
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(m:movie) where m.score>"+value+" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }
    //貌似没有好评率的指标

    @GetMapping("/ym")
    Integer byym(@RequestParam Integer year, @RequestParam Integer month){
        Session session=driver.session();
        String ym_value=year+"/"+month+'/';
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(m:movie) where m.release_time contains "+ym_value+" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }

    @GetMapping("/ys")
    Integer byys(@RequestParam Integer year, @RequestParam Integer season){
        Session session=driver.session();
        String ym_value1=year+"/"+(season-1)*3+1+'/';
        String ym_value2=year+"/"+(season-1)*3+2+'/';
        String ym_value3=year+"/"+(season-1)*3+3+'/';
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(m:movie) where m.release_time contains "+ym_value1
                                    +" or m.release_time contains "+ym_value2
                                    +" or m.release_time contains "+ym_value3
                                    +" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }

    @GetMapping("/ymd")
    Integer byymd(@RequestParam Integer startYear, @RequestParam Integer endYear,
                  @RequestParam Integer startMonth, @RequestParam Integer endMonth,
                  @RequestParam Integer startDay, @RequestParam Integer endDay){
        Session session=driver.session();
        String start=startYear+"/"+startMonth+"/"+startDay+" 00:00";
        String end=endYear+"/"+endMonth+"/"+endDay+" 00:00";
        long startTime = System.currentTimeMillis();
        Result res=session.run("match(m:movie) where m.release_time >= "+start+" and m.release_time <="+end+" return m");
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        return (int) (endTime-startTime);
    }

}
