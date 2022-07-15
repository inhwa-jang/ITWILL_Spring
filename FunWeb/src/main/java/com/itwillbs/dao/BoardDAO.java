package com.itwillbs.dao;

import java.util.List;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

public interface BoardDAO {

	void insertBoard(BoardDTO boardDTO);
	
	public Integer getMaxNum();

	List<BoardDTO> getBoardList(PageDTO pageDTO);

	int getBoardCount();

	BoardDTO getBoard(int num);

	void updateBoard(BoardDTO boardDTO);

	void deleteBoard(int num);

}
