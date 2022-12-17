package cn.edu.tongji.dwbackend.mysql.controller;



import cn.edu.tongji.dwbackend.mysql.dto.GetAcAcColla;
import cn.edu.tongji.dwbackend.mysql.dto.GetDcAcColla;
import cn.edu.tongji.dwbackend.mysql.dto.GetDcDcColla;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mysql/byColla")
public class ByColla {

    @Resource
    ActorActorRepo actorActorRepo;
    @Resource
    DirectorDirectorRepo directorDirectorRepo;
    @Resource
    ActorDirectoRepo actorDirectoRepo;

    static private boolean acac_exist(List<ViewActorActorCollaborationEntity> v,ViewActorActorCollaborationEntity item){
        for(ViewActorActorCollaborationEntity i:v ){
            if(i.getActorName1()==item.getActorName2()&&i.getActorName2()== item.getActorName1()){
                return true;
            }
        }
        return false;
    }
    static private boolean dcdc_exist(List<ViewDirectorDirectorCollaborationEntity> v,ViewDirectorDirectorCollaborationEntity item){
        for(ViewDirectorDirectorCollaborationEntity i:v ){
            if(i.getDirectorName1()== item.getDirectorName2()&&i.getDirectorName2()== item.getDirectorName1()){
                return true;
            }
        }
        return false;
    }






    @RequestMapping(value = "count/acac",method = RequestMethod.GET)
    public ResponseEntity<GetAcAcColla> getActorColla(@RequestParam int collaTime){

        long start=System.currentTimeMillis();
        GetAcAcColla result=new GetAcAcColla();

        List<ViewActorActorCollaborationEntity> actorActorMovieEntities = actorActorRepo.findAll();
        long end=System.currentTimeMillis();
        List<ViewActorActorCollaborationEntity> l=new ArrayList<>();
        for(ViewActorActorCollaborationEntity a:actorActorMovieEntities){
            if(a.getCollaborateCount()>=collaTime&&acac_exist(l,a)==false){
                l.add(a);
            }

        }
        result.setData(l);
        result.setTime(end-start);
        result.setNum(l.size());
        return new ResponseEntity<>(result, HttpStatus.OK); }

    @RequestMapping(value = "count/colla/dcac",method = RequestMethod.GET)
    public ResponseEntity<GetDcAcColla> getDicAcColla(@RequestParam int collaTime){

        GetDcAcColla result=new GetDcAcColla();
        long start=System.currentTimeMillis();
        List<ViewActorDirectorCollaborationEntity> viewActorDirectorCollaborationEntities=actorDirectoRepo.findAll();
        long end=System.currentTimeMillis();
        List<ViewActorDirectorCollaborationEntity> l=new ArrayList<>();
        for(ViewActorDirectorCollaborationEntity a:viewActorDirectorCollaborationEntities){
            if(a.getCollaborateCount()>=collaTime){
                l.add(a);
            }
        }
        result.setData(l);
        result.setTime(end-start);
        result.setNum(l.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "count/colla/dcdc",method = RequestMethod.GET)
    public ResponseEntity<GetDcDcColla> getDicDic(@RequestParam int collaTime){
        GetDcDcColla result=new GetDcDcColla();
        long start=System.currentTimeMillis();
        List<ViewDirectorDirectorCollaborationEntity> viewDirectorDirectorCollaborationEntities = directorDirectorRepo.findAll();
        long end=System.currentTimeMillis();
        List<ViewDirectorDirectorCollaborationEntity> l=new ArrayList<>();
        for(ViewDirectorDirectorCollaborationEntity a:viewDirectorDirectorCollaborationEntities){
            if(a.getCollaborateCount()>=collaTime&&dcdc_exist(l,a)==false){
                l.add(a);
            }
        }
        result.setData(l);
        result.setTime(end-start);
        result.setNum(l.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
