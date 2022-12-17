package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.TimeEntity;
import cn.edu.tongji.dwbackend.mysql.entity.TimeMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TimeMovieRepo
        extends JpaRepository<TimeMovieEntity,Integer>, JpaSpecificationExecutor<TimeMovieEntity> {
}
