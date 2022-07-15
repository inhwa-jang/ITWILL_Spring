package com.itwillbs.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;

@Controller
public class BoardController {
	
	@Inject
	private BoardService boardService;
	
	//upload경로
	@Resource(name = "uploadPath")
	private String uploadPath;

	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String insert() {
		// /WEB-INF/views/board/writeForm.jsp
		return "board/writeForm";
	}
	
	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardDTO boardDTO) {
		//디비 insert
		boardService.insertBoard(boardDTO);
		
		return "redirect:/board/list";
	}
	
	//http://localhost:8080/myweb2/board/list?pageNum=2
	//http://localhost:8080/myweb2/board/list
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		int pageSize=10;
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}
		PageDTO pageDTO=new PageDTO();
		pageDTO.setPageSize(pageSize);
		pageDTO.setPageNum(pageNum);
		
		List<BoardDTO> boardList=boardService.getBoardList(pageDTO);
		
		int currentPage=Integer.parseInt(pageDTO.getPageNum());
		int count=boardService.getBoardCount();
		int pageBlock=10;
		int startPage=(currentPage-1)/pageBlock*pageBlock+1;
		int endPage=startPage+pageBlock-1;
		int pageCount= count/pageSize+ (count%pageSize==0?0:1);
		if(endPage > pageCount){
			endPage=pageCount;
		}
		pageDTO.setCount(count);
		pageDTO.setPageBlock(pageBlock);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setPageCount(pageCount);
		
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageDTO", pageDTO);
		
		// /WEB-INF/views/board/list.jsp
		return "board/list";
	}
	
	//http://localhost:8080/myweb2/board/content?num=2
	@RequestMapping(value = "/board/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Model model) {
		int num= Integer.parseInt(request.getParameter("num"));
		
		BoardDTO boardDTO=boardService.getBoard(num);
		
		model.addAttribute("boardDTO", boardDTO);
		
		// /WEB-INF/views/board/content.jsp
		return "board/content";
	}
	
	@RequestMapping(value = "/board/fwrite", method = RequestMethod.GET)
	public String finsert() {
		// /WEB-INF/views/board/fwriteForm.jsp
		return "board/fwriteForm";
	}
	
	@RequestMapping(value = "/board/fwritePro", method = RequestMethod.POST)
	public String fwritePro(HttpServletRequest request, MultipartFile file) throws Exception{
		BoardDTO boardDTO=new BoardDTO();
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setPass(request.getParameter("pass"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		// 프로그램 설치 fileupload , commons-io, annotation-api
		// 설정
		
		// 파일이름 => 랜덤문자_파일이름
		UUID uuid=UUID.randomUUID();
		String fileName=uuid.toString()+"_"+file.getOriginalFilename();
		// 업로드 파일을 => resources/upload 폴더 복사
		File uploadFile=new File(uploadPath,fileName);
		FileCopyUtils.copy(file.getBytes(), uploadFile);
		
		boardDTO.setFile(fileName);
		
		//디비 insert
		boardService.insertBoard(boardDTO);
		
		return "redirect:/board/list";
	}
	
}
