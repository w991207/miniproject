package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.dtos.UserFileBoardDto;

@Mapper
public interface UserFileMapper {

	public boolean insertFileBoard(UserFileBoardDto dto);
	
	public UserFileBoardDto getFileInfo(int file_seq);

	
	
}
