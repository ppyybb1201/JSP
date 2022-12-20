package kr.ac.ync.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.ac.ync.domain.DepartmentVO;
import kr.ac.ync.domain.DeptTitleVO;

public class DepartmentDAOImpl implements DepartmentDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	   public DepartmentDAOImpl() {
		      try {
		         Context ctx = new InitialContext();
		         Context envContext = (Context) ctx.lookup("java:/comp/env");
		         dataFactory = (DataSource) envContext.lookup("jdbc/mysql_employees");
		      } catch (Exception e) {
		         System.out.println("!!!!! DepartmentDAOImpl ERROR !!!!!");
		         e.printStackTrace();
		      }
		   }

	@Override
	public List<DepartmentVO> getList() {
		List<DepartmentVO> list = new ArrayList<>();
		try {
			con = dataFactory.getConnection();
			String sql = "SELECT * FROM departments ORDER BY dept_no ";

			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				DepartmentVO departmentVO = new DepartmentVO();
				departmentVO.setDept_no(rs.getString("dept_no"));
				departmentVO.setDept_name(rs.getString("dept_name"));
				list.add(departmentVO);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<DeptTitleVO> getDeptTitleList(String dept_no) {
		List<DeptTitleVO> list = new ArrayList<>();
		try {
			con = dataFactory.getConnection();
			String sql = "SELECT * FROM dept_title WHERE dept_no = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dept_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				DeptTitleVO deptTitleVO = new DeptTitleVO();
				deptTitleVO.setDept_no(rs.getString("dept_no"));
				deptTitleVO.setTitle(rs.getString("title"));
				list.add(deptTitleVO);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
