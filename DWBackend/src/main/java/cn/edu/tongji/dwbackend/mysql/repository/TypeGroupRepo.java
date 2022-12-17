package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.TimeEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewTypeActorGroupEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ViewTypeActorGroupEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeGroupRepo
        extends JpaRepository<ViewTypeActorGroupEntity, ViewTypeActorGroupEntityPK>,
        JpaSpecificationExecutor<ViewTypeActorGroupEntity> {

    @Query("select p from ViewTypeActorGroupEntity p where p.type=?1 order by p.comment desc ")
    List<ViewTypeActorGroupEntity> findTopByCommentAndType(String type);
}
