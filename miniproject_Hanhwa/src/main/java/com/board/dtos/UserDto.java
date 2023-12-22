package com.board.dtos;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "userDto")
public class UserDto {

   private String id;
   private String name;
   private String password;
   private String address;
   private String role;
   private String enabled;
   private Date regdate;
   public UserDto() {
      super();
      // TODO Auto-generated constructor stub
   }
   public UserDto(String id, String name, String password, String address, String role, String enabled, Date regdate) {
      super();
      this.id = id;
      this.name = name;
      this.password = password;
      this.address = address;
      this.role = role;
      this.enabled = enabled;
      this.regdate = regdate;
   }
   @Override
   public String toString() {
      return "UserDto [id=" + id + ", name=" + name + ", password=" + password + ", address=" + address + ", role="
            + role + ", enabled=" + enabled + ", regdate=" + regdate + "]";
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getAddress() {
      return address;
   }
   public void setAddress(String address) {
      this.address = address;
   }
   public String getRole() {
      return role;
   }
   public void setRole(String role) {
      this.role = role;
   }
   public String getEnabled() {
      return enabled;
   }
   public void setEnabled(String enabled) {
      this.enabled = enabled;
   }
   public Date getRegdate() {
      return regdate;
   }
   public void setRegdate(Date regdate) {
      this.regdate = regdate;
   }
   
   
   
}