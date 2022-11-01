package com.prj.lms.controller.item;

import com.prj.lms.requestDto.item.ItemCreateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class ItemController {
    @PostMapping(value = "/admin/item/new")
    public Object memberSignup(@RequestBody @Valid ItemCreateRequestDto itemCreateRequestDto,
                               BindingResult bindingResult){
        // 검증하려는 객체의 앞에 @Valid 어노테이션을 선언하고, 파라미터로 bindingResult 객체를 추가.
        // 검사 후 결과는 bindingResult에 담아줌. bindingResult.hasErrors()를 호출하여 에러 확인.
        if(bindingResult.hasErrors()){
            System.out.println("bindingResult.hasErrors()");
            return bindingResult.getFieldErrors();
        }

        return "success";
    }

}
