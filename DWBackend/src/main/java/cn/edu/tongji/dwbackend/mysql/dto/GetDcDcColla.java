package cn.edu.tongji.dwbackend.mysql.dto;


import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorDirectorCollaborationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDcDcColla {
    List<ViewDirectorDirectorCollaborationEntity> data;
    int num;
    long time;
}
