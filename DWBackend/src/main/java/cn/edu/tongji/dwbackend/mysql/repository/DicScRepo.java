package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorDirectorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorDirectorCollaborationEntityPK;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorScoreTypeEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorScoreTypeEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DicScRepo
        extends JpaRepository<ViewDirectorScoreTypeEntity, ViewDirectorScoreTypeEntityPK>, JpaSpecificationExecutor<ViewDirectorScoreTypeEntity> {

    List<ViewDirectorScoreTypeEntity> findAll();
}
