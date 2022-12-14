package cn.edu.tongji.dwbackend.mysql.controller;

import cn.edu.tongji.dwbackend.mysql.dto.GetNameList;
import cn.edu.tongji.dwbackend.mysql.dto.GetNum;
import cn.edu.tongji.dwbackend.mysql.entity.DirectorMovieEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorDirectorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorDirectorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.repository.*;
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

    @Resource
    MovieRepository movieRepository;

    @RequestMapping(value = "count/movie",method = RequestMethod.GET)
    public ResponseEntity<GetNum> getDirectorMovieByDirectorName(@RequestParam(value = "directorName")String directorName){
        long start=System.currentTimeMillis();
        GetNum result=new GetNum();

        List<DirectorMovieEntity> directorMovieEntities = directorMovieRepository.findByDirectorName(directorName);

        long end=System.currentTimeMillis();
        result.setTime(end-start);

        result.setNum(directorMovieEntities.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "list/movie",method = RequestMethod.GET)
    public ResponseEntity<GetNameList > getDirectorMovieListByDirectorName(@RequestParam(value = "directorName")String directorName){

        long start=System.currentTimeMillis();
        GetNameList result=new GetNameList();

        List<String> name=new ArrayList<>();
        System.out.println(directorName);
        List<DirectorMovieEntity> directorMovieEntities = directorMovieRepository.findByDirectorName(directorName);
        for(DirectorMovieEntity d:directorMovieEntities){
            name.add(movieRepository.findFirstByMovieId(d.getMovieId()).getMovieName());

        }

        long end=System.currentTimeMillis();
        result.setTime(end-start);
        result.setData(name);
        result.setNum(name.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
