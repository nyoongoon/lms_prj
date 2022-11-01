package com.prj.lms.requestDto.item;

import com.prj.lms.domain.item.ItemEntity;
import com.prj.lms.domain.item.itemConstant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ItemCreateRequestDto {
    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message ="재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgCreateRequestDto> ItemImgCreateRequestDtoList = new ArrayList<>();

    private List<Long>  itemImgIdList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public ItemEntity createItemEntity(){
        return modelMapper.map(this, ItemEntity.class);
    }

    public static ItemCreateRequestDto of(ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemCreateRequestDto.class);
    }
}
