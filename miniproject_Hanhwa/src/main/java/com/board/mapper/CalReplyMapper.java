package com.board.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.dtos.CalDto;


@Mapper
public interface CalReplyMapper {

   public int insertCalReplyBoard(CalDto dto) throws Exception;
   
   public List<CalDto> getCalReplyBoard(int seq) throws Exception;
}