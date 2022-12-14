package cn.edu.tongji.dwbackend.mysql.controller;

import cn.edu.tongji.dwbackend.mysql.dto.GetNum;
import cn.edu.tongji.dwbackend.mysql.entity.TimeEntity;
import cn.edu.tongji.dwbackend.mysql.repository.TimeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/mysql/byTime")
public class ByTime {
    @Resource
    TimeRepository timeRepository;
    @RequestMapping(value = "count/yearMonth",method = RequestMethod.GET)
    public ResponseEntity<GetNum> getTimeByYearAndMonth(@RequestParam(value = "year")Integer year, @RequestParam(value = "month")Integer month){
        System.out.println(year);

        long start=System.currentTimeMillis();
        GetNum result=new GetNum();
        List<TimeEntity> timeEntities = timeRepository.findAllByYearAndMonth(year,month);

        result.setNum(timeEntities.size());
        long end=System.currentTimeMillis();
        result.setTime(end-start);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "count/yearSeason",method = RequestMethod.GET)
    public ResponseEntity<GetNum> getTimeByYearAndSeason(@RequestParam(value = "year")Integer year, @RequestParam(value = "season")Integer season){

        long start=System.currentTimeMillis();
        GetNum result=new GetNum();

        System.out.println(year);
        List<TimeEntity> timeEntities = timeRepository.findAllByYearAndSeason(year,season);


        result.setNum(timeEntities.size());
        long end=System.currentTimeMillis();
        result.setTime(end-start);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }


    @RequestMapping(value = "count/yearMonthDay",method = RequestMethod.GET)
    public ResponseEntity<GetNum> getTimeByYearMonthDay(@RequestParam Integer startYear, @RequestParam Integer endYear
                                                         ,@RequestParam Integer startMonth, @RequestParam Integer endMonth,
                                                        @RequestParam Integer startDay, @RequestParam Integer endDay){

        long start=System.currentTimeMillis();
        GetNum result=new GetNum();

        int i=0;
        List<TimeEntity> timeEntities = timeRepository.findAll();
        for(TimeEntity t:timeEntities){
            if(t.getYear()>startYear&&t.getYear()<endYear){
                i++;
                continue;
            }
            if(t.getYear()==startYear){
                if(t.getMonth()>startMonth){
                    i++;
                    continue;
                }
                if(t.getMonth()==startMonth){
                    if(t.getDay()>=startDay){
                        i++;
                        continue;
                    }
                }
            }
            if(t.getYear()==endYear){
                if(t.getMonth()<endMonth){
                    i++;
                    continue;
                }
                if(t.getMonth()==endMonth){
                    if(t.getDay()<=endDay){
                        i++;
                    }
                }
            }
        }
        System.out.println(timeEntities.size());
        result.setNum(i);
        long end=System.currentTimeMillis();
        result.setTime(end-start);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
