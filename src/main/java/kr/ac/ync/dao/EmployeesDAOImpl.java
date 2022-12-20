package kr.ac.ync.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.ac.ync.domain.Criteria;
import kr.ac.ync.domain.EmployeeVO;

public class EmployeesDAOImpl implements EmployeesDAO {

   private Connection con;
   private PreparedStatement pstmt;
   private DataSource dataFactory;

   public EmployeesDAOImpl() {
      try {
         Context ctx = new InitialContext();
         Context envContext = (Context) ctx.lookup("java:/comp/env");
         dataFactory = (DataSource) envContext.lookup("jdbc/mysql_employees");
      } catch (Exception e) {
         System.out.println("!!!!! EmployeesDAOImpl ERROR !!!!!");
         e.printStackTrace();
      }
   }

   @Override
   public List<EmployeeVO> getList(Criteria cri) {
      List<EmployeeVO> list = new ArrayList<>();
      try {
         con = dataFactory.getConnection();
         String sql = "SELECT * FROM employees " + "where emp_no > 0 " + "order by emp_no desc limit ?, ? ";

         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, (cri.getPageNum() - 1) * cri.getAmount());
         pstmt.setInt(2, cri.getAmount());
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            EmployeeVO emp = new EmployeeVO();
            emp.setEmp_no(rs.getLong("emp_no"));
            emp.setFirst_name(rs.getString("first_name"));
            emp.setLast_name(rs.getString("last_name"));
            emp.setGender(rs.getString("gender"));
            emp.setBirth_date(rs.getDate("birth_date"));
            emp.setHire_date(rs.getDate("hire_date"));
            list.add(emp);
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
   public int getTotalCount(Criteria cri) {
      int count = 0;
      List<Object> params = new ArrayList<>();
      try {
         con = dataFactory.getConnection();
         StringBuilder sql = new StringBuilder("SELECT count(emp_no) AS cnt FROM employees WHERE emp_no > 0 ");

         if (cri.getKeyword() != null && cri.getType() != null) {
            if (cri.getType().equals("E")) {
               sql.append("AND emp_no LIKE CONCAT('%', ?, '%') ");
               params.add(cri.getKeyword());
            } else if (cri.getType().equals("N")) {
               sql.append("AND first_name LIKE CONCAT('%', ?, '%') ");
               params.add(cri.getKeyword());
            }
         }
         pstmt = con.prepareStatement(sql.toString());
         setParameter(pstmt, params.toArray());
         System.out.println(pstmt);
         ResultSet rs = pstmt.executeQuery();

         if (rs.next())
            count = rs.getInt("cnt");
         rs.close();
         pstmt.close();
         con.close();
      } catch (Exception e) {
      }
      return count;
   }

   @Override
   public List<EmployeeVO> getListWithPaging(Criteria cri) {
      List<EmployeeVO> list = new ArrayList<>();
      List<Object> params = new ArrayList<>();
      try {
         con = dataFactory.getConnection();
         StringBuilder sql = new StringBuilder("SELECT * FROM employees WHERE emp_no > 0 ");
         if (cri.getKeyword() != null && cri.getType() != null) {
            if (cri.getType().equals("E")) {
               sql.append("AND emp_no LIKE CONCAT('%', ?, '%') ");
               params.add(cri.getKeyword());
            } else if (cri.getType().equals("N")) {
               sql.append("AND first_name LIKE CONCAT('%', ?, '%') ");
               params.add(cri.getKeyword());
            }
         }
         sql.append("ORDER BY emp_no DESC LIMIT ?, ? ");
         params.add(Integer.valueOf((cri.getPageNum() - 1) * cri.getAmount()));
         params.add(Integer.valueOf(cri.getAmount()));
         pstmt = con.prepareStatement(sql.toString());
         setParameter(pstmt, params.toArray());
         System.out.println(pstmt);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            EmployeeVO emp = new EmployeeVO();
            emp.setEmp_no(rs.getLong("emp_no"));
            emp.setFirst_name(rs.getString("first_name"));
            emp.setLast_name(rs.getString("last_name"));
            emp.setGender(rs.getString("gender"));
            emp.setBirth_date(rs.getDate("birth_date"));
            emp.setHire_date(rs.getDate("hire_date"));
            list.add(emp);
         }
         rs.close();
         pstmt.close();
         con.close();
      } catch (Exception e) {
      }
      return list;
   }

   private void setParameter(PreparedStatement statement, Object[] parameters) {
      try {
         for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            int index = i + 1;
            if (parameter instanceof Long) {
               statement.setLong(index, (Long) parameter);
            } else if (parameter instanceof String) {
               statement.setString(index, (String) parameter);
            } else if (parameter instanceof Integer) {
               statement.setInt(index, (Integer) parameter);
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }
}