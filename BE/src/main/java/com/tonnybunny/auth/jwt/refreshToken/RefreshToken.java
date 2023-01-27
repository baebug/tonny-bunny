package com.tonnybunny.auth.jwt.refreshToken;


import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Data
public class RefreshToken { // Entity라서 나중에 이동

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "refresh_token_seq")
	private Long seq;

	@Column(name = "refresh_token", length = 500)
	private String refreshToken;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;


	public RefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}