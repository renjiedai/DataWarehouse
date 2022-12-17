package cn.edu.tongji.dwbackend.neo4j.service.impl;

import com.example.neo4jtest.model.Director;
import com.example.neo4jtest.model.Movie;
import com.example.neo4jtest.repository.noderepository.DirectorRepository;
import com.example.neo4jtest.service.nodeservice.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public Director findByName(String name) {
        return directorRepository.findByName(name);
    }


    @Override
    public List<String> getNum(String name) {
        System.out.println(name);
        Director dd=findByName(name);
        System.out.println(dd);
        System.out.println("-------------------------");
        Set<Movie> m=directorRepository.findByName(name).getMovies();
        List<String> ret=new ArrayList<>();
        ret.add(Integer.toString(m.size())); //第一个是总数，后面是<100个名字
        int num=0;
        for(Movie mv:m){
            if(num++<100)
                ret.add(mv.getName());
            else break;
        }
        return ret;
    }



}
