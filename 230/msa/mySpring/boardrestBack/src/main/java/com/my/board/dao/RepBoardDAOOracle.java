package com.my.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.board.vo.RepBoard;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
@Repository
public class RepBoardDAOOracle implements RepBoardDAOInterface {
	@Autowired
	private SqlSessionFactory sqlSessionFactory; 

	@Override
	public List<RepBoard> findAll() throws FindException {
		return findAll(1,5);
	}
	
	public List<RepBoard>findAll(int currentPage, int cntPerPage) throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Integer> map = new HashMap<>();
			map.put("currentPage", cntPerPage);//1페이지 검색 
			map.put("cntPerPage", 5); //페이지별 보여줄 목록수 //5
			
			List<RepBoard> list = session.selectList("com.my.board.RepBoardMapper.findAll",map);
			if(list.size()==0) {
				throw new FindException("게시글이 없습니다.");
			}
			return list;
		}catch(Exception e){
			
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}

	@Override
	public RepBoard findByNo(int boardNo) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			RepBoard rb= 
			session.selectOne("com.my.board.RepBoardMapper.findByNo",boardNo);
			if(rb==null) {
				throw new FindException("게시글이 없습니다.");
			}
			
			return rb;
		}catch(Exception e){
			
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}

	@Override
	public List<RepBoard> findByWord(String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plusViewCount(int boardNo) throws ModifyException {
		
			SqlSession session = null;
			try {
				session = sqlSessionFactory.openSession();
				RepBoard rb= 
				session.selectOne("com.my.board.RepBoardMapper.findByNo",boardNo);
				int rowCnt = session.update("com.my.board.RepBoardMapper.plusViewCount",boardNo);
				if(rowCnt==0) {//rowCnt 처리건수 
					throw new ModifyException("처리할 게시글이 없습니다.");
					
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new ModifyException("게시글이 없습니다.");
			}finally {
				if(session != null) {
					session.close();
				}
			}
			
		}

	

	@Override
	public void modify(RepBoard repBoard) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int rowCnt = session.update("com.my.board.RepBoardMapper.modify",repBoard);
			if(rowCnt == 0) {//rowCnt는 처리건수 
				throw new ModifyException("게시글이 없습니다.");
				
			}
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			
		}
		}

	}

	@Override
	public void remove(RepBoard repBoard) throws RemoveException {
		removeReply(repBoard.getBoardNo()); //글 삭제 
		removeWrite(repBoard);//답글들 삭제 
	}
	/**
	 * 
	 * @param repBoard
	 * @throws RemoveException
	 */
	private void removeWrite(RepBoard repBoard) throws RemoveException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			System.out.println("remove-2");
			session.delete("com.my.board.RepBoardMapper.removeWrite",repBoard);
			System.out.println("remove-3");
			
			
				
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			
			}
		}

	}

		
	
	
	
	/**
	 * 부모글 번호에 해당하는 답글을 모두 삭제한다(계층형 쿼리 활용)
	 * @param boardNo 부모글번호 
	 * @throws RemoveException
	 */
	private void removeReply(int boardNo) throws RemoveException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			System.out.println("remove-4");
			session.delete("com.my.board.RepBoardMapper.removeReply",boardNo);
			System.out.println("remove-5");
		}catch(Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			
			}
		}

	}

	@Override
	public void add(RepBoard repBoard) throws AddException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.my.board.RepBoardMapper.add", repBoard);
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
		
	}
	
	@Override
	public int findCount() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			return session.selectOne("com.my.board.RepBoardMapper.findCount");
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}


}