package cn.edu.tongji.dwbackend.mysql.controller;


import cn.edu.tongji.dwbackend.mysql.dto.GetNameList;
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
    public ResponseEntity<GetNameList> getCombine2(@RequestParam String directorName, @RequestParam double rate, @RequestParam String type){


        long start=System.currentTimeMillis();
        GetNameList result=new GetNameList();

        List<ViewDirectorScoreTypeEntity> viewDirectorScoreTypeEntities = dicScRepo.findAll();
        List<String> name=new ArrayList<>();

        for (ViewDirectorScoreTypeEntity v:viewDirectorScoreTypeEntities){
            if(v.getNegativeRate()>rate&&v.getDirector().equals(v.getDirector())&&v.getType().equals(type)&&name.contains(v.getMovieName())==false){
                name.add(v.getMovieName());
            }
        }
        long end=System.currentTimeMillis();
        result.setTime(end-start);
        result.setData(name);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "list/score",method = RequestMethod.GET)
    public ResponseEntity<GetNameList> getCombine3(@RequestParam String start,
                                                    @RequestParam String end ,@RequestParam String name,double score){


        long startTime=System.currentTimeMillis();
        GetNameList result=new GetNameList();


        List<ViewActorScoreTimeEntity> viewActorScoreTimeEntities = acScRepo.findAll();
        List<String> nameL=new ArrayList<>();

        for (ViewActorScoreTimeEntity v:viewActorScoreTimeEntities){

            if(v.getYear()>=Integer.parseInt(start)&&v.getYear()<=Integer.parseInt(end)&&v.getScore()>=score&&name.equals(v.getActor())&&nameL.contains(v.getMovieName())==false){
                nameL.add(v.getMovieName());
            }


        }

        long endTime=System.currentTimeMillis();
        result.setTime(endTime-startTime);
        result.setData(nameL);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
