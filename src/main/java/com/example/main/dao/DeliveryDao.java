package com.example.main.dao;

import com.example.main.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/12/10.
 */
@Repository
public interface DeliveryDao extends JpaRepository<Delivery,Integer> {
@Query(value = "select * from delivery d where d.c_id=:cid",nativeQuery = true)
    List<Delivery> QueryByCid(@Param("cid") int cid);
}
