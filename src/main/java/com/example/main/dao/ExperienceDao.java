package com.example.main.dao;

import com.example.main.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */
@Repository
public interface ExperienceDao extends JpaRepository<Experience,Integer> {
@Query(value = "select * from experience e left join resume r on e.r_id=r.r_id where u_id=:uid order by e_id desc",nativeQuery = true)
    List<Experience> QueryByUser(@Param("uid") int uid);
}
