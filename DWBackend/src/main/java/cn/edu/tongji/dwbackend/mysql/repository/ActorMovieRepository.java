package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.ActorMovieEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ActorMovieEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActorMovieRepository extends JpaRepository<ActorMovieEntity, ActorMovieEntityPK>, JpaSpecificationExecutor<ActorMovieEntity> {
    List<ActorMovieEntity> findAllByActorName(String actorName);
    List<ActorMovieEntity> findAllByActorNameAndStarring(String actorName, Boolean starring);
}
