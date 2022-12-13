package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.ViewActorActorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorActorCollaborationEntityPK;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorScoreTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AcScRepo
        extends JpaRepository<ViewActorScoreTimeEntity, Integer>, JpaSpecificationExecutor<ViewActorScoreTimeEntity> {
    List<ViewActorScoreTimeEntity> findAll();
}
