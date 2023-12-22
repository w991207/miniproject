package com.board.dtos;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias(value = "FreeBoardDto")
public class FreeBoardDto {

   private int board_seq;
   private String id;
   private String title;
   private String content;
   private Date regdate;
   private int refer;
   private int step;
   private int depth;
   private String readCount;
   private String delflag;
   
   private List<FileBoardDto> fileBoardDto;
   
   public List<FileBoardDto> getFileBoardDto() {
      return fileBoardDto;
   }

   public void setFileBoardDto(List<FileBoardDto> fileBoardDto) {
      this.fileBoardDto = fileBoardDto;
   }

   public FreeBoardDto() {
      super();
      // TODO Auto-generated constructor stub
   }

   public FreeBoardDto(int board_seq, String id, String title, String content, Date regdate, int refer, int step,
         int depth, String readCount, String delflag) {
      super();
      this.board_seq = board_seq;
      this.id = id;
      this.title = title;
      this.content = content;
      this.regdate = regdate;
      this.refer = refer;
      this.step = step;
      this.depth = depth;
      this.readCount = readCount;
      this.delflag = delflag;
   }

   @Override
   public String toString() {
      return "NewsBoardDto [board_seq=" + board_seq + ", id=" + id + ", title=" + title + ", content=" + content
            + ", regdate=" + regdate + ", refer=" + refer + ", step=" + step + ", depth=" + depth + ", readCount="
            + readCount + ", delflag=" + delflag + "]";
   }

   public int getBoard_seq() {
      return board_seq;
   }

   public void setBoard_seq(int board_seq) {
      this.board_seq = board_seq;
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

   public Date getRegdate() {
      return regdate;
   }

   public void setRegdate(Date regdate) {
      this.regdate = regdate;
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

   public String getReadCount() {
      return readCount;
   }

   public void setReadCount(String readCount) {
      this.readCount = readCount;
   }

   public String getDelflag() {
      return delflag;
   }

   public void setDelflag(String delflag) {
      this.delflag = delflag;
   }
   
   
   
   
}
















