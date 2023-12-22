package com.board.command;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public class NewsInsertBoardCommand {
   
   private int seq;

   private String id;
   
   @NotBlank(message = "제목을 입력해주세요")
   private String title;
   
   @NotBlank(message = "내용을 입력해주세요")
   private String content;

   private String delflag;
   
   private Date regDate;
   private int refer;
   private int step;
   private int depth;
   private int readCount;
   
   public NewsInsertBoardCommand() {
      super();
      // TODO Auto-generated constructor stub
   }

   public NewsInsertBoardCommand(int seq, String id, @NotBlank(message = "제목을 입력해주세요") String title,
         @NotBlank(message = "내용을 입력해주세요") String content, String delflag, Date regDate, int refer, int step, int depth,
         int readCount) {
      super();
      this.seq = seq;
      this.id = id;
      this.title = title;
      this.content = content;
      this.delflag = delflag;
      this.regDate = regDate;
      this.refer = refer;
      this.step = step;
      this.depth = depth;
      this.readCount = readCount;
   }

   @Override
   public String toString() {
      return "NewsInsertBoardCommand [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content
            + ", delflag=" + delflag + ", regDate=" + regDate + ", refer=" + refer + ", step=" + step + ", depth="
            + depth + ", readCount=" + readCount + "]";
   }

   public int getSeq() {
      return seq;
   }

   public void setSeq(int seq) {
      this.seq = seq;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getDelflag() {
      return delflag;
   }

   public void setDelflag(String delflag) {
      this.delflag = delflag;
   }

   public Date getRegDate() {
      return regDate;
   }

   public void setRegDate(Date regDate) {
      this.regDate = regDate;
   }

   public int getRefer() {
      return refer;
   }

   public void setRefer(int refer) {
      this.refer = refer;
   }

   public int getStep() {
      return step;
   }

   public void setStep(int step) {
      this.step = step;
   }

   public int getDepth() {
      return depth;
   }

   public void setDepth(int depth) {
      this.depth = depth;
   }

   public int getReadCount() {
      return readCount;
   }

   public void setReadCount(int readCount) {
      this.readCount = readCount;
   }
   
   

}