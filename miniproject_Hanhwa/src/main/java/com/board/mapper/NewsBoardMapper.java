package com.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.board.dtos.NewsBoardDto;


@Mapper
public interface NewsBoardMapper {

   
      // 글 목록
      public List<NewsBoardDto> getAllList(Map<String,String>map);
      
      // 글 상세 조회
      public NewsBoardDto getBoard(int board_seq);
      
      // 글 추가
      public boolean insertBoard(NewsBoardDto dto);
      
      // 글 수정
      public boolean updateBoard(NewsBoardDto dto);
      
      // 글 삭제
      public boolean mulDel(String[] seqs);
      
      
      // 조회수
      public boolean readCount(int seq);
      
      //페이징
      public int getPCount();
}