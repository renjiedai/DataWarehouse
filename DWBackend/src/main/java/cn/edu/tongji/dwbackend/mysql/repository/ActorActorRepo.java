package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActorActorRepo extends JpaRepository<ViewActorActorCollaborationEntity, ViewActorActorCollaborationEntityPK>, JpaSpecificationExecutor<ViewActorActorCollaborationEntity> {
    List<ViewActorActorCollaborationEntity> findAll();
}
