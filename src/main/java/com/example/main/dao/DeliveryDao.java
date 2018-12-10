package com.example.main.dao;

import com.example.main.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/12/10.
 */
public interface DeliveryDao extends JpaRepository<Delivery,Long> {
}
