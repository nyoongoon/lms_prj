package com.prj.lms.requestDto.item;

import com.prj.lms.domain.item.ItemEntity;
import org.modelmapper.ModelMapper;

public class ItemImgCreateRequestDto {
    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;
    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgCreateRequestDto of (ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemImgCreateRequestDto.class);
    }
}
