package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.dtos.FreeBoardDto;



@Mapper
public interface FreeBoardReplyMapper {
	   public int insertReplyBoard(FreeBoardDto dto) throws Exception;
	   
	   public List<FreeBoardDto> showReplyBoard(int seq) throws Exception;
}
