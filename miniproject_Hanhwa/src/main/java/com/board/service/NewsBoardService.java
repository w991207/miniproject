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
import com.board.dtos.NewsBoardDto;
import com.board.mapper.FileMapper;
import com.board.mapper.NewsBoardMapper;
import com.board.mapper.NewsBoardReplyMapper;

import jakarta.servlet.http.HttpServletRequest;



@Service
public class NewsBoardService {

   @Autowired
   private NewsBoardMapper newsBoardMapper;
   @Autowired
   private FileMapper fileMapper;
   @Autowired
   private FileService fileService;
   @Autowired
   private NewsBoardReplyMapper newsBoardReplyMapper;
   
   public List<NewsBoardDto> getAllList(String pnum){
	   Map<String,String>map=new HashMap<>();
	   map.put("pnum", pnum);
      return newsBoardMapper.getAllList(map);
   }
   
   //글 추가, 파일 업로드 및 파일 정보 추가
      public void insertBoard(NewsInsertBoardCommand insertBoardCommand, MultipartRequest multipartRequest, HttpServletRequest request) throws IllegalStateException, IOException {
         
         // command -> dto 데이터 옮겨담기
         NewsBoardDto boardDto = new NewsBoardDto();
         boardDto.setId(insertBoardCommand.getId());
         boardDto.setTitle(insertBoardCommand.getTitle());
         boardDto.setContent(insertBoardCommand.getContent());
         boardDto.setDelflag(insertBoardCommand.getDelflag());
         
         //새글을 추가할 때 파라미터로 전달된 boardDto 객체에 자동으로 증가된 board_seq값이 저장
         newsBoardMapper.insertBoard(boardDto); // 새글 추가
         //첨부된 파일이 있는 경우
         if(!multipartRequest.getFiles("filename").get(0).isEmpty()) {
            // 파일 저장경로 설정 : 절대경로, 상대경로
            String filepath = request.getSession().getServletContext().getRealPath("upload");
            System.out.println("파일저장경로:"+filepath);
            
            // 파일 업로드 작업은 FileService 쪽에서 업로드하고 업로드된 객체 반환
            List<FileBoardDto> uploadFileList = fileService.uploadFiles(filepath, multipartRequest);
            
            // 파일 정보를 DB에 추가
            // 글 추가할때 board_seq 증가된 값 --> file 정보를 추가할 때 사용
            // Testboard : board_seq PK         board_seq FK
            for(FileBoardDto fDto : uploadFileList) {
               fileMapper.insertFileBoard(new FileBoardDto(0, boardDto.getBoard_seq(), fDto.getOrigin_filename(), fDto.getStored_filename()));
            }
         }
         
      }
      
      public NewsBoardDto getBoard(int board_seq) {
         return newsBoardMapper.getBoard(board_seq);
      }
      
      // 수정하기
      public boolean updateBoard(NewsUpdateBoardCommand updateBoardCommand) {
               //command:UI --> DTO:DB
               NewsBoardDto dto = new NewsBoardDto();
               dto.setBoard_seq(updateBoardCommand.getBoard_seq());
               dto.setTitle(updateBoardCommand.getTitle());
               dto.setContent(updateBoardCommand.getContent());
               return newsBoardMapper.updateBoard(dto);
      }
      
      public boolean mulDel(String[] seqs) {
         return newsBoardMapper.mulDel(seqs);
      }
      
      public boolean readCount(int seq) {
         
         return newsBoardMapper.readCount(seq);
      }
      public boolean insertReply(InsertReplyCommand insertCommand) throws Exception {
          
          NewsBoardDto dto=new NewsBoardDto();
          dto.setBoard_seq(insertCommand.getBoard_seq());
          dto.setId(insertCommand.getId());
          dto.setContent(insertCommand.getContent());
          
          int count=newsBoardReplyMapper.insertReplyBoard(dto);
         
          return count>0?true:false;
       }
   
    public List<NewsBoardDto> showReply(int seq) throws Exception{    
        return newsBoardReplyMapper.showReplyBoard(seq);
    }

	public int getPCount() {
		// TODO Auto-generated method stub
		return newsBoardMapper.getPCount();
	}
}














