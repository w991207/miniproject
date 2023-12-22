package com.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.board.dtos.UserFileBoardDto;
import com.board.mapper.UserFileMapper;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserFileService {

   @Autowired
   private UserFileMapper fileMapper;
   
   //파일업로드하기
   public List<UserFileBoardDto> uploadFiles(String uploadPath
                      ,MultipartRequest multipartRequest) 
                     throws IllegalStateException, IOException{
      //여러개의 파일들을 List에 담는 코드
      List<MultipartFile> multipartFiles
                        =multipartRequest.getFiles("filename");
      
      //업로드된 파일들의 정보(원본명,저장명)를 담아줄 LIST 
      List<UserFileBoardDto> uploadFileList=new ArrayList<>();
      
      for(MultipartFile multipartFile:multipartFiles) {
         //원본파일명 구하기
         String origin_filename=multipartFile.getOriginalFilename();
         //저장파일명 구하기: 32자리생성(1234567812345678~.txt)
         String stored_filename=UUID.randomUUID()
         +origin_filename.substring(origin_filename.indexOf("."));
         //파일저장 경로 구하기(파일명을 바꾸기위한 작업)
         String fileuploadUrl=uploadPath+"/"+stored_filename;
         multipartFile.transferTo(new File(fileuploadUrl));//upload실행
         //각각의 파일정보를 list에 저장하는 코드
         uploadFileList.add(new UserFileBoardDto(0,0,origin_filename,stored_filename));
      }
      
      return uploadFileList;
   }
   
   public UserFileBoardDto getFileInfo(int file_seq) {
      return fileMapper.getFileInfo(file_seq);
   }

   public void fileDownload(String origin_filename, String stored_filename, HttpServletRequest request,
         HttpServletResponse response) throws UnsupportedEncodingException {
      
      String filePath = request.getSession().getServletContext().getRealPath("upload");
      response.setContentType("application/octet-stream");
      response.setHeader("Content-Disposition", "attachment; fileName="+URLEncoder.encode(origin_filename,"UTF-8"));
      response.setHeader("Content-transfer", "binary");
      
      File file = new File(filePath+"/"+stored_filename);
      FileInputStream fs = null;
      ServletOutputStream out = null;
      
      try {
         fs = new FileInputStream(file);
         out= response.getOutputStream(); 
         FileCopyUtils.copy(fs, out);
         response.flushBuffer();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
		try {
			out.flush();
			out.close();
			fs.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
      
   }
   
}










