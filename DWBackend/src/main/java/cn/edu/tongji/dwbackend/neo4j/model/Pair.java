package cn.edu.tongji.dwbackend.neo4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pair {
    private String name1;
    private String name2;
    private String time;
}
