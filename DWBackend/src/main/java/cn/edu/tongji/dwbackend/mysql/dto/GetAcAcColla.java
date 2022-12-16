package cn.edu.tongji.dwbackend.mysql.dto;

import cn.edu.tongji.dwbackend.mysql.entity.ViewActorActorCollaborationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAcAcColla {
    List<ViewActorActorCollaborationEntity> data;
    int num;
    long time;
}
