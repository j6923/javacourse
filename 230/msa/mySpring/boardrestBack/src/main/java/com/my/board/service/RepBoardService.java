package com.my.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.RepBoardDAOInterface;
import com.my.board.vo.RepBoard;
import com.my.dto.PageDTO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Service
public class RepBoardService {
	@Autowired
	private RepBoardDAOInterface dao; 
//	public List<RepBoard> findAll() throws FindException{
//		return dao.findAll();
//	}
//	
//	public List<RepBoard> findAll(int currentPage) throws FindException{
//		int cntPerPage = 5;
//		return dao.findAll(currentPage, cntPerPage);
//	}
	
	public PageDTO<RepBoard> findAll() throws FindException{
		return findAll(1);
	}

	public PageDTO<RepBoard> findAll(int currentPage) throws FindException{
		String url = "/board/list";
		int totalCnt = dao.findCount();
		List<RepBoard> list = dao.findAll(currentPage, PageDTO.CNT_PER_PAGE);
		PageDTO<RepBoard> pageDTO = new PageDTO<>(url, currentPage, totalCnt, list);
		return pageDTO;
	}

	
	
	
	public RepBoard findByNo(int boardNo) throws FindException{
		try {
			dao.plusViewCount(boardNo);//조회수 먼저 증가 시키고 밑에 함수 호출 
			RepBoard rb = dao.findByNo(boardNo);  //작성, 테스트하세요. 
			return rb;
		}catch(ModifyException e) {
			e.printStackTrace();
			throw new FindException("조회수 증가실패:" + e.getMessage());
		}
		
		
		
		
	}
	
	
	public void write(RepBoard repBoard) throws AddException{
		repBoard.setParentNo(0);
		dao.add(repBoard);
	}
	
	/**
	 * 답글쓰기 
	 * @param repBoard
	 * @throws AddException
	 */

	public void reply(RepBoard repBoard) throws AddException{
		if(repBoard.getParentNo()==0) {
			throw new AddException("답글쓰기의 부모글번호가 없습니다.");
		}
		dao.add(repBoard);
		
	}
	
	public void modify(RepBoard repBoard) throws ModifyException{
		dao.modify(repBoard);
	}
	public void remove(RepBoard repBoard) throws RemoveException{
		dao.remove(repBoard);
		
	}

}
