package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.ViewActorActorEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorActorEntityPK;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorDirectorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorDirectorCollaborationEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActorDirectoRepo extends JpaRepository<ViewActorDirectorCollaborationEntity, ViewActorDirectorCollaborationEntityPK>, JpaSpecificationExecutor<ViewActorDirectorCollaborationEntity> {
    List<ViewActorDirectorCollaborationEntity> findAll();
}
