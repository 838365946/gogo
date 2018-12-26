package com.example.main.dao;

import com.example.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {
    @Query(value = "select * from user u where u.phone_number =:phonenumber and u.password=:password ",nativeQuery = true)
     User Login(@Param("phonenumber") String phone_number,@Param("password") String password);
    @Query(value = "select * from user u where u.phone_number =:phonenumber",nativeQuery = true)
     User WxLogin(@Param("phonenumber")String phone_number);
       @Query(value = "select * from user u left join delivery d on u.id=d.u_id LEFT JOIN company c on d.c_id=c.c_id  where c.c_id=:c_id ",nativeQuery = true)
       List<User> findbyid1(@Param("c_id") int id);
       @Query(value = "select * from user u where u.username = :username",nativeQuery = true)
    User Check(String username);

}
