package cn.edu.tongji.dwbackend.mysql.controller;


import cn.edu.tongji.dwbackend.mysql.entity.ViewActorActorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorDirectorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorDirectorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.repository.ActorActorRepo;
import cn.edu.tongji.dwbackend.mysql.repository.ActorDirectoRepo;
import cn.edu.tongji.dwbackend.mysql.repository.DirectorDirectorRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mysql/byAcDc")
public class ByAcDc {

    @Resource
    ActorActorRepo actorActorRepo;
    @Resource
    DirectorDirectorRepo directorDirectorRepo;
    @Resource
    ActorDirectoRepo actorDirectoRepo;

    @RequestMapping(value = "count/acac",method = RequestMethod.GET)
    public ResponseEntity<List<ViewActorActorCollaborationEntity>> gerActorColla(){

        List<ViewActorActorCollaborationEntity> actorActorMovieEntities = actorActorRepo.findAll();
        List<ViewActorActorCollaborationEntity> result=new ArrayList<>();
        for(ViewActorActorCollaborationEntity a:actorActorMovieEntities){
            if(a.getCollaborateCount()>20){
                result.add(a);
            }

        }

        return new ResponseEntity<>(result, HttpStatus.OK); }

    @RequestMapping(value = "count/colla/dcac",method = RequestMethod.GET)
    public ResponseEntity<List<ViewActorDirectorCollaborationEntity> > getDicAcColla(){
        List<ViewActorDirectorCollaborationEntity> viewActorDirectorCollaborationEntities=actorDirectoRepo.findAll();
        List<ViewActorDirectorCollaborationEntity> result=new ArrayList<>();
        for(ViewActorDirectorCollaborationEntity a:viewActorDirectorCollaborationEntities){
            if(a.getCollaborateCount()>15){
                result.add(a);
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "count/colla/dcdc",method = RequestMethod.GET)
    public ResponseEntity<List<ViewDirectorDirectorCollaborationEntity>> getDicDic(){

        List<ViewDirectorDirectorCollaborationEntity> viewDirectorDirectorCollaborationEntities = directorDirectorRepo.findAll();
        List<ViewDirectorDirectorCollaborationEntity> result=new ArrayList<>();
        for(ViewDirectorDirectorCollaborationEntity a:viewDirectorDirectorCollaborationEntities){
            if(a.getCollaborateCount()>15){
                result.add(a);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
