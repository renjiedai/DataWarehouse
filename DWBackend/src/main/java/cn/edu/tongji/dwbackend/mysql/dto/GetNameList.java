package cn.edu.tongji.dwbackend.mysql.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetNameList {

    List<String> data;

    int num;

    long time;
    
}
