package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;


@Controller
public class BoardController {
	//객체생성
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String insert() {
		// /WEB-INF/views/center/write.jsp
		return "center/write";
	}
	
	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardDTO boardDTO) {
		//디비 insert
		boardService.insertBoard(boardDTO);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		int pageSize=10;
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		int currentPage=Integer.parseInt(pageNum);
		PageDTO pageDTO=new PageDTO();
		pageDTO.setPageNum(pageNum);
		pageDTO.setPageSize(pageSize);
		pageDTO.setCurrentPage(currentPage);
		
		List<BoardDTO> boardList=boardService.getBoardList(pageDTO);
		
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
		
		
		model.addAttribute("boardList",boardList);
		model.addAttribute("pageDTO", pageDTO);
		
		// /WEB-INF/views/center/notice.jsp
		return "center/notice";
	}
	
	@RequestMapping(value = "/board/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Model model) {
		int num=Integer.parseInt(request.getParameter("num"));
		
		BoardDTO boardDTO=boardService.getBoard(num);
		
		model.addAttribute("boardDTO", boardDTO);
		
		// /WEB-INF/views/center/content.jsp
		return "center/content";
	}
	
	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public String update(HttpServletRequest request, Model model) {
		int num=Integer.parseInt(request.getParameter("num"));
		
		BoardDTO boardDTO=boardService.getBoard(num);
		
		model.addAttribute("boardDTO", boardDTO);
		
		// /WEB-INF/views/center/update.jsp
		return "center/update";
	}
	
	@RequestMapping(value = "/board/updatePro", method = RequestMethod.POST)
	public String updatePro(BoardDTO boardDTO) {
		
		boardService.updateBoard(boardDTO);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int num=Integer.parseInt(request.getParameter("num"));
		
		boardService.deleteBoard(num);
		
		return "redirect:/board/list";
	}
	
	
	
}
