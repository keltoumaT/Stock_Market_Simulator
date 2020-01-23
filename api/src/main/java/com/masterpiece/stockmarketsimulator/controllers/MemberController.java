package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.dtos.MemberDto;
import com.masterpiece.stockmarketsimulator.dtos.MemberViewDto;
import com.masterpiece.stockmarketsimulator.services.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping
    protected void create(@Valid @RequestBody MemberDto dto){
        memberService.create(dto);
    }

    @PutMapping("/{id}")
    protected void update (@PathVariable("id")Long id, @Valid @RequestBody MemberDto dto){
        memberService.update(id, dto);
    }

    @DeleteMapping("/{id]}")
    protected void delete (@PathVariable("id")Long id){
        memberService.delete(id);
    }


    @GetMapping
    protected List<MemberViewDto> getAll(){
      return   memberService.getAll();
    }

    @GetMapping("/{id}")
    protected MemberViewDto getOne(@PathVariable("id")Long id){
        return memberService.getOne(id);
    }

}
