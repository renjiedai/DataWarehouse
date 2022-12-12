package cn.edu.tongji.dwbackend.mysql.controller;

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
    public ResponseEntity<Integer> getMovieByMovieName(@RequestParam(value = "movieName")String movieName){
        MovieEntity movieEntities = movieRepository.findByMovieName(movieName);
        return new ResponseEntity<>((int)movieEntities.getMovieFormatNum(), HttpStatus.OK);
    }
}
