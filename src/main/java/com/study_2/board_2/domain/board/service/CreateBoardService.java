package com.study_2.board_2.domain.board.service;

import com.study_2.board_2.domain.board.dto.req.CreateBoardReqDto;
import com.study_2.board_2.domain.board.entity.repository.BoardRepository;
import com.study_2.board_2.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
// Transactional : 트랜잭션 처리를 담당 (★서비스로직에서 무조건 해줘야함)
// 디폴트 값이 서비스 로직이 정상적으로 완료되지않으면 디비에 적용이 되지않습니다.
// 작업 중 오류가 발생하면 자동으로 롤백(되돌리기)되도록 보장
@Service
@RequiredArgsConstructor
// RequiredArgsConstructor : 생성자 주입을 자동으로 생성해주는 롬복(Lombok) 어노테이션
// 필드 중에서 final로 선언된 멤버 변수에 대해 자동으로 생성자를 만들어 줍니다.
// 이거 있으면 @Autowired 따로 안적어줘도 된다.
public class CreateBoardService {

//    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;
    // Mybatis에서는 Mapper 인터페이스를 사용하지만
    // JPA에서는 JpaRepository 인터페이스를 사용한다.

    public void createBoard(CreateBoardReqDto req, User user) {
        boardRepository.save(req.of(user));

        // boardMapper.createBoard(req.of());
        // Dto에서 변환된 Board 객체를 사용하여 데이터베이스에 새 행을 추가 -> service에서 dto를 entity로 형태를 바꿈
    }

}
