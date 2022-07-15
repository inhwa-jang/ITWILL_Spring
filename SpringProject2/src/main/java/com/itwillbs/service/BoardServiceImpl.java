package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Service
public class BoardServiceImpl implements BoardService{

	//객체생성
	@Inject
	private BoardDAO boardDAO;
	
	@Override
	public void insertBoard(BoardDTO boardDTO) {
		// name pass subject content => 폼에서 가져옴
		// num readcount date => 값 설정
		boardDTO.setReadcount(0);
		boardDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		// max(num)+1
		if(boardDAO.getMaxNum()==null) {
			boardDTO.setNum(1);
		}else {
			boardDTO.setNum(boardDAO.getMaxNum()+1);
		}
		
		
		boardDAO.insertBoard(boardDTO);
	}

	@Override
	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		// pageDTO => pageSize, pageNum
		int currentPage=Integer.parseInt(pageDTO.getPageNum());
		int startRow =(currentPage-1)*pageDTO.getPageSize()+1;
		int endRow=startRow+pageDTO.getPageSize()-1;
		
		pageDTO.setCurrentPage(currentPage);
//		pageDTO.setStartRow(startRow);
		pageDTO.setEndRow(endRow);
		
		//디비 조회 limit startRow-1, pageSize
		pageDTO.setStartRow(startRow-1);
		
		return boardDAO.getBoardList(pageDTO);
	}

	@Override
	public int getBoardCount() {
		return boardDAO.getBoardCount();
	}

	@Override
	public BoardDTO getBoard(int num) {
		return boardDAO.getBoard(num);
	}

}
