package com.example.main.dao;

import com.example.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/12/6.
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {
    @Query(value = "select * from user u where u.phone_number =:phonenumber and u.password=:password ",nativeQuery = true)
     User Login(@Param("phonenumber") String phone_number,@Param("password") String password);
    @Query(value = "select * from user u where u.phone_number =:phonenumber",nativeQuery = true)
     User WxLogin(@Param("phonenumber")String phone_number);

}
