package kr.ac.ync.service;

import java.util.List;

import kr.ac.ync.domain.DepartmentVO;
import kr.ac.ync.domain.DeptTitleVO;

public interface DepartmentService {
	public List<DepartmentVO> getList();
	public List<DeptTitleVO> getDeptTitleList(String dept_no);
}
