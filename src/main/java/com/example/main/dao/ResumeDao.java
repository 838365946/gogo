package com.example.main.dao;

import com.example.main.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/12/6.
 */
@Repository
public interface ResumeDao  extends JpaRepository<Resume,Long>{
    @Query(value = "select * from resume r where r.phone_number = :phone order by r_id desc",nativeQuery = true)
    Resume QueryResumeByPhoneNumber(@Param("phone")String phone);
    @Query(value = "select * from resume r where r.u_id=:uid order by r_id desc",nativeQuery = true)
    Resume QueryByUid(@Param("uid") int uid);


}
