package com.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.board.command.InsertCalReplyCommand;
import com.board.command.InsertReplyCommand;
import com.board.command.NewsDelBoardCommand;
import com.board.command.NewsInsertBoardCommand;
import com.board.command.NewsUpdateBoardCommand;
import com.board.dtos.CalDto;
import com.board.dtos.FileBoardDto;
import com.board.dtos.NewsBoardDto;
import com.board.service.FileService;
import com.board.service.NewsBoardService;
import com.board.utils.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/news")
public class NewsBoardController {

   @Autowired
   private NewsBoardService newsBoardService;
   
   @Autowired
   private FileService fileService;
   
   

   @GetMapping(value = "/boardList")
   public String NewsBoardList(Model model,HttpServletRequest request,HttpServletResponse response,String pnum) {
      System.out.println("글목록 보기");
      
	  HttpSession session=request.getSession();
		if(pnum==null) {
			pnum=(String)session.getAttribute("pnum");//현재 조회중인 페이지번호
		}else {
			//새로 페이지를 요청할 경우 세션에 저장
			session.setAttribute("pnum", pnum);
		}
	  //페이지 수 구하기 
      int pcount=newsBoardService.getPCount();
	  model.addAttribute("pcount", pcount);
				
	  //---페이지에 페이징 처리 기능 추가
	  //필요한 값: 페이지수, 페이지번호, 페이지범위(페이지수)
	  Map<String, Integer>map=Paging.pagingValue(pcount, pnum, 10);
	  model.addAttribute("pMap", map);
      
      List<NewsBoardDto> list = newsBoardService.getAllList(pnum);
      model.addAttribute("list", list);
      model.addAttribute("delBoardCommand", new NewsDelBoardCommand());
      
      return "news/newsboardList";
   }
   
   @GetMapping(value = "/newsBoardInsert")
   public String boardInsertForm(Model model) {
      System.out.println("글추가폼 이동");
      model.addAttribute("insertBoardCommand", new NewsInsertBoardCommand());
      
      return "news/newsBoardInsertForm";
   }
   
   @PostMapping(value = "/newsBoardInsert")
   public String boardInsert(@Validated NewsInsertBoardCommand insertBoardCommand,
                           BindingResult result, MultipartRequest multipartRequest,
                           HttpServletRequest request,
                           Model model) throws IllegalStateException, IOException {
      
      if(result.hasErrors()) {
         System.out.println("글을 모두 입력하세요");
         return "news/newsBoardInsertForm";
      }
      
      newsBoardService.insertBoard(insertBoardCommand, multipartRequest, request);
      
      return "redirect:/news/boardList";
   }
   @GetMapping(value = "/newsBoardDetail")
   public String boardDetail(int board_seq, Model model, NewsUpdateBoardCommand newsUpdateBoardCommand) {
      NewsBoardDto dto = newsBoardService.getBoard(board_seq);
      model.addAttribute("updateBoardCommand", new NewsUpdateBoardCommand());
      model.addAttribute("dto",dto);
      int seq = newsUpdateBoardCommand.getBoard_seq();
      
      
      newsBoardService.readCount(seq);//조회수 증가

      return "news/newsBoardDetail";
   }
   
   @PostMapping(value = "/newsBoardUpdate")
   public String boardUpdate(@Validated NewsUpdateBoardCommand updateBoardCommand
                              ,BindingResult result) {
      System.out.println("수정시작");
      if(result.hasErrors()) {
         System.out.println("수정내용을 모두 입력하세요");
         return "news/newsBoardDetail";
      }
      
      newsBoardService.updateBoard(updateBoardCommand);
      
      return "redirect:/news/newsBoardDetail?board_seq="+updateBoardCommand.getBoard_seq();
      
   }
   
   @GetMapping(value = "/download")
   public void download(int file_seq, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
      FileBoardDto fdto = fileService.getFileInfo(file_seq);
      
      fileService.fileDownload(fdto.getOrigin_filename(),fdto.getStored_filename(),request,response);
   }
   
   @RequestMapping(value="mulDel",method = {RequestMethod.GET, RequestMethod.POST})
   public String mulDel(@Validated NewsDelBoardCommand delBoardCommand
                   ,BindingResult result
                      , Model model,String pnum) {
      if(result.hasErrors()) {
         System.out.println("최소하나 체크하기");
         List<NewsBoardDto> list=newsBoardService.getAllList(pnum);
         model.addAttribute("list", list);
         return "news/newsboardList";
      }
      newsBoardService.mulDel(delBoardCommand.getSeq());
      System.out.println("글삭제함");
      return "redirect:/news/boardList";
   }
   
   @ResponseBody
   @GetMapping(value = "/addReplyBoard")
   public String addCalReply(@Validated InsertReplyCommand insertCommand,
                       BindingResult result) throws Exception {
      System.out.println("댓글추가");
      if(result.hasErrors()) {
         System.out.println("글을 모두 입력해야 함");
         return "news/NewsBoardDetail";
      }
      System.out.println(insertCommand);
//      logger.info("탈출ㅋ");
      newsBoardService.insertReply(insertCommand);
      
     // return "redirect:/schedule/calBoardDetail";
      return "";
   }
   

   @ResponseBody
   @GetMapping(value = "/showReplyBoard")
   public Map<String, List<NewsBoardDto>> NewsBoardList(@Validated InsertCalReplyCommand insertCalCommand, BindingResult result,  Model model, int seq) throws Exception {
      System.out.println("댓글 보여주기");
      List<NewsBoardDto> list = newsBoardService.showReply(seq);
      Map<String, List<NewsBoardDto>> map = new HashMap<>();
      map.put("list", list);

      model.addAttribute("insertCalCommand", new InsertCalReplyCommand());
      
      return map;
   }
   
   
}