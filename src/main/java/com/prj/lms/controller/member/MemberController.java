package com.prj.lms.controller.member;

import com.prj.lms.requestDto.item.ItemRequestDto;
import com.prj.lms.requestDto.member.MemberSignupRequestDto;
import com.prj.lms.responseDto.item.ItemResponseDto;
import com.prj.lms.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/ex02")
    public ItemResponseDto example02(@ModelAttribute ItemRequestDto itemRequestDto){
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        itemResponseDto.setItemDetail("상품 상세 설명");
        itemResponseDto.setItemNm("테스트 상품 1");
        itemResponseDto.setPrice(10000);
        itemResponseDto.setRegTime(LocalDateTime.now());
        return itemResponseDto;
    }

    @PostMapping(value = "/new")
    public Object memberSignup(@RequestBody @Valid MemberSignupRequestDto memberSignupRequestDto,
                                BindingResult bindingResult){
        // 검증하려는 객체의 앞에 @Valid 어노테이션을 선언하고, 파라미터로 bindingResult 객체를 추가.
        // 검사 후 결과는 bindingResult에 담아줌. bindingResult.hasErrors()를 호출하여 에러 확인.
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldErrors();
        }
        memberService.saveMember(memberSignupRequestDto);
        return "success";
    }

    /*@GetMapping("/login")
    public void login() {
        System.out.println("GET successfully called on /login resource");
    }*/


}
