package com.example.membersite.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.membersite.model.domain.User;
import com.example.membersite.model.dto.RegisterDto;


@Mapper
public interface UserMapper {

	List<User> findByIdAndPassword(String id,String password);
	
	List<User> findByLoginUserId(String id);

	int deletedById(String id);

	int insert(RegisterDto registerDto);
	
	int findById(@Param("id") String ID);
}
