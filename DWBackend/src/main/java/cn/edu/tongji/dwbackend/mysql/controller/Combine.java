package cn.edu.tongji.dwbackend.mysql.controller;


import cn.edu.tongji.dwbackend.mysql.entity.DirectorMovieEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorScoreTimeEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorScoreTypeEntity;
import cn.edu.tongji.dwbackend.mysql.repository.AcScRepo;
import cn.edu.tongji.dwbackend.mysql.repository.DicScRepo;
import cn.edu.tongji.dwbackend.mysql.repository.DirectorMovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mysql/combine")
public class Combine {


    @Resource
    DicScRepo dicScRepo;

    @Resource
    AcScRepo acScRepo;


    @RequestMapping(value = "list/rate",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getCombine2(@RequestParam String directorName,@RequestParam double rate,@RequestParam String type){
        List<ViewDirectorScoreTypeEntity> viewDirectorScoreTypeEntities = dicScRepo.findAll();
        List<String> result=new ArrayList<>();

        for (ViewDirectorScoreTypeEntity v:viewDirectorScoreTypeEntities){
            if(v.getNegativeRate()>rate&&v.getDirector().equals(v.getDirector())&&v.getType().equals(type)){
                result.add(v.getMovieName());
            }


        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "list/score",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getCombine3(@RequestParam String start,
                                                    @RequestParam String end ,@RequestParam String name,double score){
        List<ViewActorScoreTimeEntity> viewActorScoreTimeEntities = acScRepo.findAll();
        List<String> result=new ArrayList<>();

        for (ViewActorScoreTimeEntity v:viewActorScoreTimeEntities){

            if(v.getYear()>=Integer.parseInt(start)&&v.getYear()<=Integer.parseInt(end)&&v.getScore()>=score&&name.equals(v.getActor())){
                result.add(v.getMovieName());
            }


        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
