package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.HelperInfoImageEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class HelperInfoImageResponseDto {

	private Long seq;
	private String imagePath;


	public static HelperInfoImageResponseDto fromEntity(HelperInfoImageEntity helperInfoImage) {
		return HelperInfoImageResponseDto.builder()
		                                 .seq(helperInfoImage.getSeq()) // seq로 순서 확인
		                                 .imagePath(helperInfoImage.getImagePath())
		                                 .build();
	}


	public static List<HelperInfoImageResponseDto> fromEntityList(List<HelperInfoImageEntity> helperInfoImageList) {
		List<HelperInfoImageResponseDto> result = new ArrayList<>();
		for (HelperInfoImageEntity helperInfoImage : helperInfoImageList) {
			HelperInfoImageResponseDto helperInfoImageResponseDto = HelperInfoImageResponseDto.fromEntity(helperInfoImage);
			result.add(helperInfoImageResponseDto);
		}
		return result;
	}

}
