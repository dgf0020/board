package com.study_2.board_2.domain.board.controller;

import com.study_2.board_2.domain.board.dto.req.CreateBoardReqDto;
import com.study_2.board_2.domain.board.dto.req.UpdateBoardReqDto;
import com.study_2.board_2.domain.board.dto.resp.GetBoardRespDto;
import com.study_2.board_2.domain.board.facade.BoardFacade;
import com.study_2.board_2.domain.board.service.CreateBoardService;
import com.study_2.board_2.domain.board.service.DeleteBoardService;
import com.study_2.board_2.domain.board.service.GetBoardListPaginationService;
import com.study_2.board_2.domain.board.service.GetBoardService;
import com.study_2.board_2.domain.board.service.UpdateBoardService;
import com.study_2.board_2.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Board", description = "게시판 API")
@RestController
// @RestController = @Controller + @ResponseBody
@RequestMapping("/board")
@RequiredArgsConstructor
// @RequiredArgsConstructor를 사용하면 의존성 주입을 간편하게 할 수 있다 (service에서도 사용함)
public class BoardController {

  private final BoardFacade boardFacade;

  // 게시글 생성
  @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
  @PostMapping
  public ResponseEntity<String> createBoard(@RequestBody CreateBoardReqDto req,
      HttpSession session) {
    try {
      User user = (User) session.getAttribute("user");

      if (user == null) {
        return ResponseEntity.status(401).body("로그인 해주세요");
      }

      boardFacade.createBoard(req, user);
      return ResponseEntity.status(HttpStatus.CREATED).body("게시글 생성 완료");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // 게시글 상세 조회
  @Operation(summary = "게시글 상세 조회", description = "게시글의 상세 정보를 조회합니다.")
  @GetMapping("/{id}")
  public ResponseEntity<GetBoardRespDto> getBoard(@PathVariable Long id) {
    try {
      return ResponseEntity.ok().body(boardFacade.getBoard(id));
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  // 게시글 목록 조회
  @Operation(summary = "게시글 목록 조회", description = "게시글의 전체 목록을 조회합니다.")
  @GetMapping("/list")
  public ResponseEntity<List<GetBoardRespDto>> getBoardList() {
    try {
      return ResponseEntity.ok().body(boardFacade.getBoardList());
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  // 게시글 삭제
  @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteBoard(@PathVariable Long id, HttpSession session) {
    try {
      User user = (User) session.getAttribute("user");

      if (user == null) {
        return ResponseEntity.badRequest().body("로그인 해주세요");
      }

      boardFacade.deleteBoard(id, user);
      return ResponseEntity.ok().body("게시글 삭제 완료");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // 게시글 수정
  @Operation(summary = "게시글 수정", description = "게시판글을 수정합니다.")
  @PutMapping("/update/{id}")
  public ResponseEntity<String> updateBoard(
      @PathVariable Long id, @RequestBody UpdateBoardReqDto req, HttpSession session) {
    try {
      User user = (User) session.getAttribute("user");

      if (user == null) {
        return ResponseEntity.badRequest().body("로그인 해주세요");
      }

      boardFacade.updateBoard(id, req, user);
      return ResponseEntity.ok().body("게시글 수정 완료!");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // 게시글 목록 조회 (Pagination)
  @Operation(summary = "페이지별 게시글 목록 조회", description = "페이지별로 게시글 목록을 조회합니다.")
  @GetMapping("/pageList")
  public ResponseEntity<List<GetBoardRespDto>> getBoardListPagination(
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "createdDate") String sortBy,
      @RequestParam(defaultValue = "DESC") String direction) {
    // defaultValue의 값은 문자열로 써야한다, pageNo가 0부터 시작한다
    return ResponseEntity.ok()
        .body(boardFacade.getBoardListPagination(pageNo, pageSize, sortBy, direction)
            .getContent());
  }

    /*
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
    */
}
