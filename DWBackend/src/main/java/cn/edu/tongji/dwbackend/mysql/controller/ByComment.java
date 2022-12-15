package cn.edu.tongji.dwbackend.mysql.controller;


import cn.edu.tongji.dwbackend.mysql.dto.GetNameList;
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
    public ResponseEntity<GetNameList> getMovieNameByScore(@RequestParam  double score ){

        long start=System.currentTimeMillis();
        GetNameList result=new GetNameList();

        List<ViewScoreMovieEntity> viewScoreMovieEntities=commentRepository.findAll();

        List<String> l=new ArrayList<>();
        int i=0;
        for(ViewScoreMovieEntity v:viewScoreMovieEntities){
            if(v.getScore()>=score){
                i++;
                if(l.size()<=100){
                    l.add(v.getMovieName());
                }

            }
        }
        result.setNum(i);
        result.setData(l);
        long end=System.currentTimeMillis();
        result.setTime(end-start);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/rate",method = RequestMethod.GET)
    public ResponseEntity<GetNameList> getMovieNameByRate(@RequestParam  double rate ){

        long start=System.currentTimeMillis();
        GetNameList result=new GetNameList();

        List<ViewScoreMovieEntity> viewScoreMovieEntities=commentRepository.findAll();
        List<String> l=new ArrayList<>();
        int i=0;
        for(ViewScoreMovieEntity v:viewScoreMovieEntities){
            if(v.getPositiveRate()>=rate){
                i++;
                if(l.size()<=100){
                    l.add(v.getMovieName());
                }

            }
        }
        result.setNum(i);
        result.setData(l);
        long end=System.currentTimeMillis();
        result.setTime(end-start);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
