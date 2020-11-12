package com.example.membersite.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.membersite.model.domain.User;

@Mapper
public interface UserMapper {

	List<User> findByIdAndPassword(@Param("id") String id,@Param("password") String password);

	void deletedById(String id);
}
