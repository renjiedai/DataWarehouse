package cn.edu.tongji.ycy.hivebackend.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ByMovieName {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String  getExample(){
        return  "hello  hello";
    }

}
