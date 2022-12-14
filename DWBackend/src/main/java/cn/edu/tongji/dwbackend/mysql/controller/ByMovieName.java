package cn.edu.tongji.dwbackend.mysql.controller;

import cn.edu.tongji.dwbackend.mysql.dto.GetNum;
import cn.edu.tongji.dwbackend.mysql.entity.MovieEntity;
import cn.edu.tongji.dwbackend.mysql.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/mysql/byMovieName")
public class ByMovieName {
    @Resource
    MovieRepository movieRepository;
    @RequestMapping(value = "count/format",method = RequestMethod.GET)
    public ResponseEntity<GetNum> getMovieByMovieName(@RequestParam(value = "movieName")String movieName){
        long start=System.currentTimeMillis();
        GetNum result=new GetNum();
        MovieEntity movieEntities = movieRepository.findByMovieName(movieName);
        long end=System.currentTimeMillis();
        result.setTime(end-start);
        result.setNum((int)movieEntities.getMovieFormatNum());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
