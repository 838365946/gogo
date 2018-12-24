package com.example.main.dao;

import com.example.main.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/12/10.
 */
@Repository
public interface DeliveryDao extends JpaRepository<Delivery,Integer> {

}
