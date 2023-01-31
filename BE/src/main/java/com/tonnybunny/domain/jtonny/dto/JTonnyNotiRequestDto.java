package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyNotiEntity;
import lombok.Data;

import java.time.LocalTime;


@Data
public class JTonnyNotiRequestDto {

	private Long clientSeq;
	private Long helperSeq;

	private String startLangCode;
	private String endLangCode;

	private LocalTime estimateTime;
	private String content;
	private String tonnySituCode;

	private Integer unitPrice;


	public JTonnyNotiEntity toEntity() {
		return (JTonnyNotiEntity) new Object();
	}

}
