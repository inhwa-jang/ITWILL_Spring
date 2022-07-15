package com.itwillbs.dao;

import java.util.List;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

public interface BoardDAO {
	//추상메서드
	public void insertBoard(BoardDTO boardDTO);
	
//	boardDAO.getMaxNum()
	public Integer getMaxNum();
	
	public List<BoardDTO> getBoardList(PageDTO pageDTO);
	
	public int getBoardCount();
	
	public BoardDTO getBoard(int num);
}
