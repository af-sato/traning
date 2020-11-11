package com.example.membersite.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.membersite.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	List<User> findByIdAndPassword(String id, String password);
}
