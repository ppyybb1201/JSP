package kr.ac.ync.dao;

import java.util.List;

import kr.ac.ync.domain.DepartmentVO;
import kr.ac.ync.domain.DeptTitleVO;

public interface DepartmentDAO {
	
	public List<DepartmentVO> getList();
	public List<DeptTitleVO> getDeptTitleList(String dept_no);
	
}
