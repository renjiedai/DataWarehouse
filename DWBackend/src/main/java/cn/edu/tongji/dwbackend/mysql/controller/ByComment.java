package cn.edu.tongji.dwbackend.mysql.controller;


import cn.edu.tongji.dwbackend.mysql.entity.DirectorMovieEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewScoreMovieEntity;
import cn.edu.tongji.dwbackend.mysql.repository.CommentRepository;
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
@RequestMapping("/mysql/byComment")
public class ByComment {

    @Resource
    CommentRepository commentRepository;

    @RequestMapping(value = "/score",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getMovieNameByScore(@RequestParam  double score ){
        List<ViewScoreMovieEntity> viewScoreMovieEntities=commentRepository.findAll();
        List<String> result=new ArrayList<>();
        for(ViewScoreMovieEntity v:viewScoreMovieEntities){
            if(v.getScore()>=score){
                result.add(v.getMovieName());
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/rate",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getMovieNameByRate(@RequestParam  double rate ){
        List<ViewScoreMovieEntity> viewScoreMovieEntities=commentRepository.findAll();
        List<String> result=new ArrayList<>();
        for(ViewScoreMovieEntity v:viewScoreMovieEntities){
            if(v.getPositiveRate()>=rate){
                result.add(v.getMovieName());
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
