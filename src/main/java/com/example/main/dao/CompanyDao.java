package com.example.main.dao;

import com.example.main.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/12/6.
 */
@Repository
public interface CompanyDao extends JpaRepository<Company,Long>{
@Query(value = "select * from company c where c.phone_number=:phone_number and c.password =:password",nativeQuery = true)
    Company CLogin(String phone_number,String password);
}
