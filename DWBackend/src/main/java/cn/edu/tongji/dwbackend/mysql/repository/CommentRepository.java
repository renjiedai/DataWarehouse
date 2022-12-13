package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.ActorMovieEntity;
import cn.edu.tongji.dwbackend.mysql.entity.ActorMovieEntityPK;
import cn.edu.tongji.dwbackend.mysql.entity.ViewScoreMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CommentRepository  extends JpaRepository<ViewScoreMovieEntity, Integer>, JpaSpecificationExecutor<ViewScoreMovieEntity> {

    List<ViewScoreMovieEntity> findAll();
}
