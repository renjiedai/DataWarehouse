package cn.edu.tongji.dwbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetNameList {

    List<String> data;

    long time;
    
}
