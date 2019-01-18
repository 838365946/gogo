package com.example.main.dao;

import com.example.main.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/12/10.
 */
@Repository
public interface DeliveryDao extends JpaRepository<Delivery,Integer> {
@Query(value = "select * from delivery d where p_id=:pid order by d_id desc",nativeQuery = true)
    List<Delivery> QueryByPID(@Param("pid")int pid);
    @Transactional
    @Modifying
    @Query(value = "update delivery d set d.d_BeViewed =:beview where d.d_id=:did",nativeQuery = true)
    int Beviewed(@Param("beview") String beview,@Param("did") int did);
    @Transactional
    @Modifying
    @Query(value = "update delivery d set d.d_pass =:pass where d.d_id=:did",nativeQuery = true)
    int Pass(@Param("pass") String pass,@Param("did") int did);
    @Transactional
    @Modifying
    @Query(value = "update delivery d set d.d_offer =:offer where d.d_id=:did",nativeQuery = true)
    int Offer(@Param("offer") String offer,@Param("did") int did);

    @Query(value = "select * from delivery d where d.d_BeViewed =:state and d.u_id=:uid order by d_id desc",nativeQuery = true)
    List<Delivery> QueryBeviewd(@Param("state")String state,@Param("uid")int uid);
    @Query(value = "select * from delivery d where d.d_Pass =:state and d.u_id=:uid order by d_id desc",nativeQuery = true)
    List<Delivery> QueryPass(@Param("state")String state,@Param("uid")int uid);
    @Query(value = "select * from delivery d where d.d_Offer =:state and d.u_id=:uid order by d_id desc",nativeQuery = true)
    List<Delivery> QueryOffer(@Param("state")String state,@Param("uid")int uid);
}
