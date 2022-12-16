package cn.edu.tongji.dwbackend.mysql.controller;

import cn.edu.tongji.dwbackend.mysql.dto.GetNameList;
import cn.edu.tongji.dwbackend.mysql.dto.GetNum;
import cn.edu.tongji.dwbackend.mysql.entity.ActorMovieEntity;
import cn.edu.tongji.dwbackend.mysql.repository.ActorMovieRepository;
import cn.edu.tongji.dwbackend.mysql.repository.MovieRepository;
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
    private final MovieRepository movieRepository;

    public ByActor(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @RequestMapping(value = "count/movie",method = RequestMethod.GET)
    public GetNum getActorMovieByActorNameAndIsstarring(@RequestParam(value = "actorName")String actorName , @RequestParam(value = "isStarring", required = false)Boolean isStarring){

        long start=System.currentTimeMillis();
        GetNum result=new GetNum();
        if(!isStarring){
            List<ActorMovieEntity> actorMovieEntities = actorMovieRepository.findAllByActorNameAndStarring(actorName,isStarring);
            result.setNum(actorMovieEntities.size());
            long end=System.currentTimeMillis();
            result.setTime(end-start);
            return result;
        }
        else{
            List<ActorMovieEntity> actorMovieEntities = actorMovieRepository.findAllByActorNameAndStarring(actorName, isStarring);
            result.setNum(actorMovieEntities.size());
            long end=System.currentTimeMillis();
            result.setTime(end-start);

            return result;
        }
    }

    @RequestMapping(value = "list/movie",method = RequestMethod.GET)
    public ResponseEntity<GetNameList> getActorMovieListByActorNameAndIsstarring(@RequestParam(value = "actorName")String actorName , @RequestParam(value = "isStarring", required = false)Boolean isStarring){
        System.out.println(isStarring);

        long start=System.currentTimeMillis();
        GetNameList result=new GetNameList();
        if(!isStarring){
            List<ActorMovieEntity> actorMovieEntities = actorMovieRepository.findAllByActorName(actorName);
            List<ActorMovieEntity> actorMovieEntities2 = actorMovieRepository.findAllByActorNameAndStarring(actorName, isStarring);
            List<ActorMovieEntity> r=new ArrayList<>();

            for(ActorMovieEntity a:actorMovieEntities){
                if(actorMovieEntities2.contains(a)==true    ){
                    r.add(a);
                }

            }
            long end=System.currentTimeMillis();
            result.setTime(end-start);
            List<String> name=new ArrayList<>();
            for(ActorMovieEntity a:r){
                name.add(movieRepository.findFirstByMovieId(a.getMovieId()).getMovieName());
            }
            result.setNum(name.size());
            result.setData(name);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            List<ActorMovieEntity> actorMovieEntities = actorMovieRepository.findAllByActorNameAndStarring(actorName, isStarring);

            long end=System.currentTimeMillis();
            result.setTime(end-start);
            List<String> name=new ArrayList<>();
            for(ActorMovieEntity a:actorMovieEntities){
                if(name.size()<=100){
                    name.add(movieRepository.findFirstByMovieId(a.getMovieId()).getMovieName());
                }

            }
            result.setNum(name.size());
            result.setData(name);


            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }







}
