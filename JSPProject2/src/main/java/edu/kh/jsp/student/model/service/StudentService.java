package edu.kh.jsp.student.model.service;

// JDBCTemplate의 static 필드/메서드 호출시 클래스명 생략을 위한 임포트
import static edu.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jsp.student.model.dao.StudentDAO;
import edu.kh.jsp.student.model.vo.Student;

public class StudentService {
	
	private StudentDAO dao = new StudentDAO();
	private Connection conn;
	
	/** 학생 전체 조회를 위한 서비스
	 * @return stdList
	 * @throws Exception
	 */
	public List<Student> selectAll() throws Exception{
		
		// 1. Connection 객체 생성
		conn = getConnection();
		
		// 2. DAO메서드 호출 후 결과 반환 받기
		List<Student> stdList = dao.selectAll(conn);
		
		// 3. 트랜잭션 처리 - DML구문일 때만 진행
		// select문에서는 생략
		
		// 4. Connection 객체 반환
		close(conn);
		
		// 5. 결과 반환
		return stdList;
	}


	/** 입력받은 학과명으로 학생 조회
	 * @param deptName
	 * @return stdList
	 * @throws Exception
	 */
	public List<Student> selectDept(String deptName) throws Exception {
		
		conn = getConnection();
		List<Student> stdList = dao.selectDept(conn, deptName);
		
		close(conn);
		return stdList;
	}


	
	
}
