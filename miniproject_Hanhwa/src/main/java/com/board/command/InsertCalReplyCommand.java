package com.board.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//일정추가 페이지에서 입력내용: ID, 일정날짜, 제목, 내용
//             <select> 2023 11 07 06 10 </select> 선택해서 입력 -> Command
//                   DB: seq, id, title, content, mdate, regdate -> DTO
//@Data
public class InsertCalReplyCommand {


   
   private String id;
   
   private int seq;
   
   private int comment_seq;
   
   
   @NotBlank(message = "내용을 입력하세요") //문자열만 가능
   private String content;


public InsertCalReplyCommand() {
   super();
   // TODO Auto-generated constructor stub
}


public InsertCalReplyCommand(String id, int seq, int comment_seq, @NotBlank(message = "내용을 입력하세요") String content) {
   super();
   this.id = id;
   this.seq = seq;
   this.comment_seq = comment_seq;
   this.content = content;
}


@Override
public String toString() {
   return "InsertCalReplyCommand [id=" + id + ", seq=" + seq + ", comment_seq=" + comment_seq + ", content=" + content
         + "]";
}


public String getId() {
   return id;
}


public void setId(String id) {
   this.id = id;
}


public int getSeq() {
   return seq;
}


public void setSeq(int seq) {
   this.seq = seq;
}


public int getComment_seq() {
   return comment_seq;
}


public void setComment_seq(int comment_seq) {
   this.comment_seq = comment_seq;
}


public String getContent() {
   return content;
}


public void setContent(String content) {
   this.content = content;
}

   

   
   


   
   
}



