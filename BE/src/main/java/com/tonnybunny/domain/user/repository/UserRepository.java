package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByEmail(String email);

}
