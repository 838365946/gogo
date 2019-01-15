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
    @Query(value="select * from position p left join company c on p.c_id=c.c_id where c.c_check_status='正常' and p.p_posi_name like CONCAT('%',:name,'%') or  p.p_addr like CONCAT('%',:name,'%') or c.c_name like CONCAT('%',:name,'%') ",nativeQuery=true)
    Page<Position> QueryByInput(@Param("name") String input, Pageable pageable);
    @Query(value = "select * from position p left join company c on p.c_id=c.c_id where c.c_check_status='正常' and p.c_id=:cid",nativeQuery = true)
    List<Position> QueryByCompany(@Param("cid")int cid);
    @Query(value = "select * from position p left join company c on p.c_id=c.c_id where c.c_check_status='正常' and p_id=:pid",nativeQuery = true)
    Position QueryById(@Param("pid")int pid);
    @Query(value = "select * from position p left join company c on p.c_id=c.c_id where c.c_check_status='正常' order by p_date desc",nativeQuery = true)
    Page<Position> findAllS(Pageable pageable);

    @Query(value = "delete from position  where p_id=:pid",nativeQuery = true)
    @Transactional
    @Modifying
    int DelPosition(@Param("pid") int pid);
}
