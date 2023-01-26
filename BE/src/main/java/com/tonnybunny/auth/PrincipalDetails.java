package com.tonnybunny.auth;


import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
public class PrincipalDetails implements UserDetails {

	private UserEntity user;


	public PrincipalDetails(UserEntity user) {
		this.user = user;
	}


	//	 우리는 권한 아직 안나눠서 필요없는 기능
	// UserDetails를 implements 한 관계로 만들어두긴 해야 한다.
	@Override
	public Collection<GrantedAuthority> getAuthorities() {

		return null;
	}


	@Override
	public String getPassword() {
		return user.getPassword();
	}


	@Override
	public String getUsername() {
		return user.getEmail(); // userid 대신 email 쓸거라서 email 가져옴
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}

}