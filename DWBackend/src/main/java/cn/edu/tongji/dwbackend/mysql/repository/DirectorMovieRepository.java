package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.DirectorMovieEntity;
import cn.edu.tongji.dwbackend.mysql.entity.DirectorMovieEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DirectorMovieRepository extends JpaRepository<DirectorMovieEntity, DirectorMovieEntityPK>, JpaSpecificationExecutor<DirectorMovieEntity> {
    List<DirectorMovieEntity> findByDirectorName(String directorName);

}
