package com.study_2.board_2.domain.board.facade;

/*
컨트롤러에 있는 여러 서비스를 Facade 클래스로 통합하여 컨트롤러가 Facade를 통해 서비스에 접근
이렇게 하면 컨트롤러는 Facade 클래스의 메서드만 호출하고, Facade는 필요한 서비스들을 이용해 내부 로직을 처리
*/

import com.study_2.board_2.domain.board.dto.req.CreateBoardReqDto;
import com.study_2.board_2.domain.board.dto.req.UpdateBoardReqDto;
import com.study_2.board_2.domain.board.dto.resp.GetBoardRespDto;
import com.study_2.board_2.domain.board.service.CreateBoardService;
import com.study_2.board_2.domain.board.service.DeleteBoardService;
import com.study_2.board_2.domain.board.service.GetBoardService;
import com.study_2.board_2.domain.board.service.UpdateBoardService;
import com.study_2.board_2.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardFacade {
  private final CreateBoardService createBoardService;
  private final GetBoardService getBoardService;
  private final DeleteBoardService deleteBoardService;
  private final UpdateBoardService updateBoardService;

  public void createBoard(CreateBoardReqDto req, User user) {
    createBoardService.createBoard(req, user);
  }

  public GetBoardRespDto getBoard(Long id) {
    return getBoardService.getBoard(id);
  }

  public List<GetBoardRespDto> getBoardList() {
    return getBoardService.getBoardList();
  }

  public void deleteBoard(Long id, User user) {
    deleteBoardService.deleteBoard(id, user);
  }

  public void updateBoard(Long id, UpdateBoardReqDto req, User user) {
    updateBoardService.updateBoard(id, req, user);
  }

  // getBoardService.getBoardListPagination(pageNO, pageSize, sortBy, direction)
  public Page<GetBoardRespDto> getBoardListPagination(
      int pageNo, int pageSize, String sortBy, String direction) {
    return getBoardService.getBoardListPagination(pageNo, pageSize, sortBy, direction);
  }
}
