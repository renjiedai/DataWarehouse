package cn.edu.tongji.dwbackend.mysql.controller;

import cn.edu.tongji.dwbackend.mysql.entity.DirectorMovieEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorDirectorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorDirectorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.repository.ActorActorRepo;
import cn.edu.tongji.dwbackend.mysql.repository.ActorDirectoRepo;
import cn.edu.tongji.dwbackend.mysql.repository.DirectorDirectorRepo;
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
@RequestMapping("/mysql/byDirector")
public class ByDirector {
    @Resource
    DirectorMovieRepository directorMovieRepository;



    @RequestMapping(value = "count/movie",method = RequestMethod.GET)
    public ResponseEntity<Integer> getDirectorMovieByDirectorName(@RequestParam(value = "directorName")String directorName){
        List<DirectorMovieEntity> directorMovieEntities = directorMovieRepository.findByDirectorName(directorName);
        return new ResponseEntity<>(directorMovieEntities.size(), HttpStatus.OK);
    }
    @RequestMapping(value = "list/movie",method = RequestMethod.GET)
    public ResponseEntity<List<DirectorMovieEntity>> getDirectorMovieListByDirectorName(@RequestParam(value = "directorName")String directorName){
        System.out.println(directorName);
        List<DirectorMovieEntity> directorMovieEntities = directorMovieRepository.findByDirectorName(directorName);
        return new ResponseEntity<>(directorMovieEntities, HttpStatus.OK);
    }


}
