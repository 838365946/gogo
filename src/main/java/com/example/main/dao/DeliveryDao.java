package com.example.main.dao;

import com.example.main.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/12/10.
 */
public interface DeliveryDao extends JpaRepository<Delivery,Long> {
@Query(value = "select d.u_id from delivery d where d.c_id=:c_id",nativeQuery = true)
List<Delivery> QuerybyCompany(@Param("c_id")int id);
 @Query(value = "select * from user u left join delivery d on u.id=d.u_id LEFT JOIN company c on d.c_id=c.c_id  where c.c_id=:cid ",nativeQuery = true)
List<Object> selectresumebycompany(@Param("cid") int cid);
}
