package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    public BoardDto create( //return 타입 항상 BoardDto
            BoardRequest boardRequest
    ){
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build()
                ;
        BoardEntity saveEntity  = boardRepository.save(entity); //데이터 베이스에 쓰고 리턴 받는 값의 타입은 Entity 인데
        return boardConverter.toDto(saveEntity); //리턴은 컨버터를 통해 Dto 로 변환해서 해줘야 한다.
    }

    public BoardDto view(Long id) { // 더 이상 BoardEntity 가 client 에 내려가는 일이 없도록
        return boardConverter.toDto(boardRepository.findByIdAndStatus(id, "REGISTERED").get());
    }
}
