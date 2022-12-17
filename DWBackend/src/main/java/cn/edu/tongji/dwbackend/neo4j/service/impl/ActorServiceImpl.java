package cn.edu.tongji.dwbackend.neo4j.service.impl;

import com.example.neo4jtest.model.Actor;
import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.repository.noderepository.ActorRepository;
import com.example.neo4jtest.service.nodeservice.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Actor findByName(String name) {
        return actorRepository.findByName(name);
    }

    @Override
    public List<String> getactnum(Actor a) {
        Set<Movie> m =a.getActmovie();
        List<String> ret=new ArrayList<>();
        ret.add(Integer.toString(m.size())); //第一个是总数，后面是<100个名字
        int num=0;
        for(Movie mv:m){
            if(num++<100) //only display the first 100 movies
                ret.add(mv.getName());
            else break;
        }
        return ret;
    }

    @Override
    public List<String> getstarnum(Actor a) {
        Set<Movie> m =a.getStarmovie();
        List<String> ret=new ArrayList<>();
        ret.add(Integer.toString(m.size())); //第一个是总数，后面是<100个名字
        int num=0;
        for(Movie mv:m){
            if(num++<100) //only display the first 100 movies
                ret.add(mv.getName());
            else break;
        }
        return ret;
    }

    @Override
    public List<String> getjoinnum(Actor a) {
        List<String> act_list=getactnum(a);
        List<String> star_list=getstarnum(a);
        List<String> ret=new ArrayList<>();
        if(act_list.isEmpty() && star_list.isEmpty()) return ret;
        if(act_list.isEmpty()) return star_list;
        if(star_list.isEmpty()) return act_list;
        int num1=Integer.parseInt(act_list.get(0));
        int num2=Integer.parseInt(star_list.get(0));
        ret.add(Integer.toString(num1+num2));
        for(int i=0;i<num1;i++){
            ret.add(act_list.get(i+1));
        }
        for(int i=0;i<num2;i++){
            ret.add(star_list.get(i+1));
        }
        return ret;
    }

}
