package com.study_2.board_2.domain.controller;

import com.study_2.board_2.domain.dto.req.CreateBoardReqDto;
import com.study_2.board_2.domain.dto.req.UpdateBoardReqDto;
import com.study_2.board_2.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// @RestController = @Controller + @ResponseBody
@RequestMapping("/board")
@RequiredArgsConstructor
// @RequiredArgsConstructor를 사용하면 의존성 주입을 간편하게 할 수 있다 (service에서도 사용함)
public class BoardController {

    private final CreateBoardService createBoardService;
    private final GetBoardService getBoardService;
    private final DeleteBoardService deleteBoardService;
    private final UpdateBoardService updateBoardService;
    private final GetBoardListPaginationService getBoardListPaginationService;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody CreateBoardReqDto req) {
    // @RequestBody를 사용하면 본문에서 JSON 데이터를 읽어와서 지정된 DTO 객체(CreateBoardReqDto)로 자동 변환해줌

        createBoardService.createBoard(req);

        return ResponseEntity.status(HttpStatus.CREATED).body("게시글 생성 완료");
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok().body(getBoardService.getBoard(id));
    }

    // 게시글 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> getBoardList() {
        return ResponseEntity.ok().body(getBoardService.getBoardList());
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        deleteBoardService.deleteBoard(id);
        return ResponseEntity.ok().body("게시글 삭제 완료");
    }

    // 게시글 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable Long id, @RequestBody UpdateBoardReqDto req) {
        updateBoardService.updateBoard(id, req);
        return ResponseEntity.ok().body("게시글 수정 완료!");
    }

    // 게시글 목록 조회 (Pagination)
    // * 수정사항 *
    // @PathVariable는 보안상의 이유로 사용한다. 페이지넘버는 requestparam을 사용한다.
    // 객체를 만들어서 쓰는것은 post,put,patch => requestbody사용하는거
    // get, delete는 웬만해서는 requestbody를 쓰지말자
    @GetMapping("/pageList")
    public ResponseEntity<?> getBoardListPagination(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
    // 클라이언트가 pageList?pagenumber=1&pagesize=10 이런 형식으로 요청을 보내야함
    // @RequestParam(name = "pageSize", defaultValue = "10") int pageSize => 기본적으로 pageSize는 10으로 정함

        return ResponseEntity.ok().body(getBoardListPaginationService.getBoardListPagination(pageNumber, pageSize));
    }
}
