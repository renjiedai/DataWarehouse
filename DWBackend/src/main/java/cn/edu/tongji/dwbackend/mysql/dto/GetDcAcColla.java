package cn.edu.tongji.dwbackend.mysql.dto;


import cn.edu.tongji.dwbackend.mysql.entity.ViewActorDirectorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorDirectorCollaborationEntityPK;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorScoreTypeEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorScoreTypeEntityPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDcAcColla {
    List<ViewActorDirectorCollaborationEntity> data;
    int num;
    long time;
}
