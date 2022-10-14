package edu.kh.jsp.student.model.dao;

import static edu.kh.jsp.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jsp.student.model.vo.Student;

public class StudentDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;
	
	
	/**
	 * Properties 이용을 위한 기본생성자 설정
	 */
	public StudentDAO() {
		try {
			String filePath = StudentDAO.class.getResource("/edu/kh/jsp/sql/student-sql.xml").getPath();
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Student> selectAll(Connection conn) throws Exception {
		
		// 결과 저장용 변수 선언
		List<Student> stdList = new ArrayList<>();

		try {
			// SQL 작성하기
			String sql = prop.getProperty("selectAll");
			
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			// SQL수행 후 결과인 ResultSet반환 받기
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) { // 조회결과가 1행 이상일 경우 while문 사용
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				stdList.add(new Student(studentNo, studentName, studentAddress, departmentName));
			}
			
		} finally {
			// 사용한 JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
		// 결과 반환
		return stdList;
	}
	
	
	

	/** 입력받은 학과명으로 학생 조회
	 * @param conn
	 * @param departmentName
	 * @return
	 */
	public List<Student> selectDept(Connection conn, String deptName) throws Exception {
		List<Student> stdList = new ArrayList<>(); 
		
		try {
			
			String sql = prop.getProperty("selectDept");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 조회결과가 1행 이상일 경우 while문 사용
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				
				stdList.add(new Student(studentNo, studentName, studentAddress, deptName));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return stdList;
	}
}
