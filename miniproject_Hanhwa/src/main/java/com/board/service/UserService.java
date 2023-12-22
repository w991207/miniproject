package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.board.command.AddUserCommand;
import com.board.command.LoginCommand;
import com.board.command.UserUpdateCommand;
import com.board.dtos.UserDto;
import com.board.mapper.UserMapper;
import com.board.status.RoleStatus;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

   @Autowired
   private UserMapper userMapper;
   
   @Autowired
   private PasswordEncoder passwordEncoder;
   
   public boolean addUser(AddUserCommand addUserCommand) {
      
      UserDto mdto = new UserDto();
      mdto.setId(addUserCommand.getId());
      mdto.setName(addUserCommand.getName());
      mdto.setPassword(passwordEncoder.encode(addUserCommand.getPassword()));
      mdto.setAddress(addUserCommand.getAddress());
      mdto.setRole(RoleStatus.EGG+"");
      
      return userMapper.addUser(mdto);
   }
   
   public String idChk(String id) {
      return userMapper.idChk(id);
   }
   
   public String login(LoginCommand loginCommand
              ,HttpServletRequest request
              ,Model model) {
      UserDto dto = userMapper.loginUser(loginCommand.getId());
      String path="user/main";
      if(dto!=null) {
         //로그인 폼에서 입력받은 패스워드값과 DB에 암호화된 패스워드 비교
         if(passwordEncoder.matches(loginCommand.getPassword()
                                 , dto.getPassword())) {
            System.out.println("패스워드 같음: 회원이 맞음");
            //session객체에 로그인 정보 저장
            request.getSession().setAttribute("mdto", dto);
            System.out.println(dto.getRole());
            if(dto.getRole().equals("EAGLE")) {
               System.out.println(loginCommand.getId());
               path = "user/adminmain";
            }
         }else {
            System.out.println("패스워드 틀림");
            model.addAttribute("msg", "패스워드를 확인하세요");
            path="home";
         }
      }else {
         System.out.println("회원이 아닙니다. ");
         model.addAttribute("msg", "아이디를 확인하세요");
         path="home";
      }
      
      return path;
   }
   
   public UserDto userInfo(LoginCommand loginCommand, HttpServletRequest request) {
      UserDto udto = (UserDto)request.getSession().getAttribute("mdto");
      String id = udto.getId();
      System.out.println(id);
      return userMapper.userInfo(id);
      
   }
   
   // 수정하기
   public boolean updateUser(UserUpdateCommand userUpdateCommand) {
      UserDto dto = new UserDto();
      dto.setName(userUpdateCommand.getName());
      dto.setRole(userUpdateCommand.getRole());
      dto.setAddress(userUpdateCommand.getAddress());
      dto.setId(userUpdateCommand.getId());
      return userMapper.updateUser(dto);
   }
   
   // 탈퇴하기
   public boolean delUser(LoginCommand loginCommand, HttpServletRequest request) {
      UserDto udto = (UserDto)request.getSession().getAttribute("mdto");
      String id = udto.getId();
      return userMapper.delUser(id);
   }
   
   
   public List<UserDto> getAllUserList(){
	      List<UserDto> list = userMapper.getAllUserList();
	      return list;
	   }
   public boolean mulDel(String[] id) {
       return userMapper.mulDel(id);
    }
}























