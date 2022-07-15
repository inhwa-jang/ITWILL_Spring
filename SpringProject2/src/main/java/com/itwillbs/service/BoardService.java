package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

public interface BoardService {
	//추상메서드
	public void insertBoard(BoardDTO boardDTO);
//	List<BoardDTO> boardList=boardService.getBoardList(pageDTO);
	public List<BoardDTO> getBoardList(PageDTO pageDTO);
//	int count=boardService.getBoardCount();
	public int getBoardCount();
//	BoardDTO boardDTO=boardService.getBoard(num);
	public BoardDTO getBoard(int num);
}
