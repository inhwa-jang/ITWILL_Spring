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
		// pass name subject content
		// readcount date num
		boardDTO.setReadcount(0);
		boardDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		if(boardDAO.getMaxNum()!=null) {
			boardDTO.setNum(boardDAO.getMaxNum()+1);
		}else {
			boardDTO.setNum(1);
		}
		
		boardDAO.insertBoard(boardDTO);
	}

	@Override
	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		// startRow 구하기 endRow 구하기
		int startRow =(pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1;
		int endRow=startRow+pageDTO.getPageSize()-1;
		
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

	@Override
	public void updateBoard(BoardDTO boardDTO) {
		boardDAO.updateBoard(boardDTO);
	}

	@Override
	public void deleteBoard(int num) {
		boardDAO.deleteBoard(num);
	}

}
