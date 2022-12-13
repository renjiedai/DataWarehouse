package cn.edu.tongji.dwbackend.mysql.controller;

import cn.edu.tongji.dwbackend.mysql.entity.ActorMovieEntity;
import cn.edu.tongji.dwbackend.mysql.entity.DirectorMovieEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorActorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorActorEntity;
import cn.edu.tongji.dwbackend.mysql.repository.ActorActorRepo;
import cn.edu.tongji.dwbackend.mysql.repository.ActorMovieRepository;
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
@RequestMapping("/mysql/byActor")
public class ByActor {
    @Resource
    ActorMovieRepository actorMovieRepository;



    @RequestMapping(value = "count/movie",method = RequestMethod.GET)
    public Integer getActorMovieByActorNameAndIsstarring(@RequestParam(value = "actorName")String actorName , @RequestParam(value = "isStarring", required = false)Boolean isStarring){
        if(!isStarring){
            List<ActorMovieEntity> actorMovieEntities = actorMovieRepository.findAllByActorName(actorName);
            return actorMovieEntities.size();
        }
        else{
            List<ActorMovieEntity> actorMovieEntities = actorMovieRepository.findAllByActorNameAndStarring(actorName, isStarring);
            return actorMovieEntities.size();
        }
    }

    @RequestMapping(value = "list/movie",method = RequestMethod.GET)
    public ResponseEntity<List<ActorMovieEntity>> getActorMovieListByActorNameAndIsstarring(@RequestParam(value = "actorName")String actorName , @RequestParam(value = "isStarring", required = false)Boolean isStarring){
        System.out.println(isStarring);
        if(!isStarring){
            List<ActorMovieEntity> actorMovieEntities = actorMovieRepository.findAllByActorName(actorName);
            return new ResponseEntity<>(actorMovieEntities, HttpStatus.OK);
        }
        else{
            List<ActorMovieEntity> actorMovieEntities = actorMovieRepository.findAllByActorNameAndStarring(actorName, isStarring);
            return new ResponseEntity<>(actorMovieEntities, HttpStatus.OK);
        }
    }







}
