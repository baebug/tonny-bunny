package com.tonnybunny.auth.jwt.refreshToken;


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

	@Column(name = "refreshToken", length = 500)
	private String refreshToken;


	public RefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}