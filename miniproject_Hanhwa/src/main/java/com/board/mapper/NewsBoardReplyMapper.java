package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.dtos.NewsBoardDto;


@Mapper
public interface NewsBoardReplyMapper {
	   public int insertReplyBoard(NewsBoardDto dto) throws Exception;
	   
	   public List<NewsBoardDto> showReplyBoard(int seq) throws Exception;
}
