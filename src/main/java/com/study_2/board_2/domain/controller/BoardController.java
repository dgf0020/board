package com.study_2.board_2.domain.controller;

import com.study_2.board_2.domain.dto.req.CreateBoardReqDto;
import com.study_2.board_2.domain.service.CreateBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
// @RestController = @Controller + @ResponseBody
@RequestMapping("/board")
@RequiredArgsConstructor
// @RequiredArgsConstructor를 사용하면 의존성 주입을 간편하게 할 수 있다 (service에서도 사용함)
public class BoardController {

    private final CreateBoardService createBoardService;

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody CreateBoardReqDto req) {
    // @RequestBody를 사용하면 본문에서 JSON 데이터를 읽어와서 지정된 DTO 객체(CreateBoardReqDto)로 자동 변환해줌

        createBoardService.createBoard(req);

        return ResponseEntity.status(HttpStatus.CREATED).body("게시판 생성 완료");
    }
}
