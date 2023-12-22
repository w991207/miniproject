package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.dtos.UserDto;

@Mapper
public interface UserMapper {

   public boolean addUser(UserDto dto);
   
   public String idChk(String id);
   
   public UserDto loginUser(String id);
   
   public UserDto userInfo(String id);
   
   // 정보 수정
   public boolean updateUser(UserDto dto);
   
   public boolean delUser(String id);
   
   public List<UserDto> getAllUserList();

   public boolean mulDel(String[] id);
}