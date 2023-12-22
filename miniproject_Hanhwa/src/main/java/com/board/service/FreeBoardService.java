package com.board.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartRequest;

import com.board.command.InsertReplyCommand;
import com.board.command.NewsInsertBoardCommand;
import com.board.command.NewsUpdateBoardCommand;
import com.board.dtos.FileBoardDto;
import com.board.dtos.FreeBoardDto;
import com.board.dtos.NewsBoardDto;
import com.board.dtos.UserFileBoardDto;
import com.board.mapper.FreeBoardMapper;
import com.board.mapper.FreeBoardReplyMapper;
import com.board.mapper.UserFileMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FreeBoardService {
   
   @Autowired
   private FreeBoardMapper freeBoardMapper;
   @Autowired
   private UserFileMapper userFileMapper;
   @Autowired
   private UserFileService userFileService;
   @Autowired
   private FreeBoardReplyMapper freeBoardReplyMapper;
   
   public List<FreeBoardDto> getAllList(String pnum){
      Map<String,String>map=new HashMap<>();
         map.put("pnum", pnum);
         return freeBoardMapper.getAllList(map);
      }
      
      //글 추가, 파일 업로드 및 파일 정보 추가
   public void insertBoard(NewsInsertBoardCommand insertBoardCommand, MultipartRequest multipartRequest, HttpServletRequest request) throws IllegalStateException, IOException {
            
            // command -> dto 데이터 옮겨담기
         FreeBoardDto boardDto = new FreeBoardDto();
         boardDto.setId(insertBoardCommand.getId());
         boardDto.setTitle(insertBoardCommand.getTitle());
         boardDto.setContent(insertBoardCommand.getContent());
         boardDto.setDelflag(insertBoardCommand.getDelflag());
         
         //새글을 추가할 때 파라미터로 전달된 boardDto 객체에 자동으로 증가된 board_seq값이 저장
         freeBoardMapper.insertBoard(boardDto); // 새글 추가
         //첨부된 파일이 있는 경우
         if(!multipartRequest.getFiles("filename").get(0).isEmpty()) {
            // 파일 저장경로 설정 : 절대경로, 상대경로
            String filepath = request.getSession().getServletContext().getRealPath("upload");
            System.out.println("파일저장경로:"+filepath);
            
            // 파일 업로드 작업은 FileService 쪽에서 업로드하고 업로드된 객체 반환
            List<UserFileBoardDto> uploadFileList = userFileService.uploadFiles(filepath, multipartRequest);
             
            // 파일 정보를 DB에 추가
            // 글 추가할때 board_seq 증가된 값 --> file 정보를 추가할 때 사용
            // Testboard : board_seq PK         board_seq FK
            for(UserFileBoardDto fDto : uploadFileList) {
               userFileMapper.insertFileBoard(new UserFileBoardDto(0, boardDto.getBoard_seq(), fDto.getOrigin_filename(), fDto.getStored_filename()));
            }
         }
         
      }
      
      public FreeBoardDto getBoard(int board_seq) {
         return freeBoardMapper.getBoard(board_seq);
      }
      
      // 수정하기
      public boolean updateBoard(NewsUpdateBoardCommand updateBoardCommand) {
               //command:UI --> DTO:DB
            FreeBoardDto dto = new FreeBoardDto();
            dto.setBoard_seq(updateBoardCommand.getBoard_seq());
            dto.setTitle(updateBoardCommand.getTitle());
            dto.setContent(updateBoardCommand.getContent());
            return freeBoardMapper.updateBoard(dto);
      }
      
      public boolean mulDel(String[] seqs) {
         return freeBoardMapper.mulDel(seqs);
      }
      
      public boolean readCount(int seq) {
         
         return freeBoardMapper.readCount(seq);
      }
      public boolean insertReply(InsertReplyCommand insertCommand) throws Exception {
             
          FreeBoardDto dto=new FreeBoardDto();
          dto.setBoard_seq(insertCommand.getBoard_seq());
          dto.setId(insertCommand.getId());
          dto.setContent(insertCommand.getContent());
             
          int count=freeBoardReplyMapper.insertReplyBoard(dto);
            
          return count>0?true:false;
      }
      
       public List<FreeBoardDto> showReply(int seq) throws Exception{    
           return freeBoardReplyMapper.showReplyBoard(seq);
       }
       
       public int getPCount() {
             // TODO Auto-generated method stub
             return freeBoardMapper.getPCount();
          }
       
}