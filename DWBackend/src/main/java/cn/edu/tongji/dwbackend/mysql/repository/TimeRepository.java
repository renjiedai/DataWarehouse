package cn.edu.tongji.dwbackend.mysql.repository;

import cn.edu.tongji.dwbackend.mysql.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TimeRepository extends JpaRepository<TimeEntity,Integer> , JpaSpecificationExecutor<TimeEntity> {
    List<TimeEntity> findAllByYearAndMonth(Integer year, Integer month);
    List<TimeEntity> findAllByYearAndSeason(Integer year, Integer season);
}
