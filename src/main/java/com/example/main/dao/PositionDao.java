package com.example.main.dao;

import com.example.main.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */
@Repository
public interface PositionDao extends JpaRepository<Position,Long>{
    @Query(value="select * from position p where p.p_posi_name like CONCAT('%',:name,'%') or  p.p_addr like CONCAT('%',:name,'%')",nativeQuery=true)
    Page<Position> QueryByInput(@Param("name") String input, Pageable pageable);
    @Query(value = "select * from position p where p.c_id=:cid",nativeQuery = true)
    List<Position> QueryByCompany(@Param("cid")int cid);
    @Query(value = "select * from position p where p_id=:pid",nativeQuery = true)
    Position QueryById(@Param("pid")int pid);
    @Transactional
    @Modifying
    @Query(value = "delete from position p where p.p_id=:pid",nativeQuery = true)
    int DelPosition(@Param("pid") int pid);
}
