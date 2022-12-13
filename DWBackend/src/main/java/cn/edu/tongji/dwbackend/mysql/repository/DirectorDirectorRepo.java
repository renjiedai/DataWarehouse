package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.ViewActorActorEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewActorActorEntityPK;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorDirectorCollaborationEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewDirectorDirectorCollaborationEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DirectorDirectorRepo extends JpaRepository<ViewDirectorDirectorCollaborationEntity, ViewDirectorDirectorCollaborationEntityPK>, JpaSpecificationExecutor<ViewDirectorDirectorCollaborationEntity> {
}
