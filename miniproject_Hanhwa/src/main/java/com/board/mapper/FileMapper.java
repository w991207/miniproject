package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.dtos.FileBoardDto;

@Mapper
public interface FileMapper {

	public boolean insertFileBoard(FileBoardDto dto);
	
	public FileBoardDto getFileInfo(int file_seq);
	
}
