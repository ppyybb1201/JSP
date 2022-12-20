package kr.ac.ync.dao;

import java.util.List;

import kr.ac.ync.domain.Criteria;
import kr.ac.ync.domain.EmployeeVO;

public interface EmployeesDAO {
	public List<EmployeeVO> getList(Criteria cri);
	
	public List<EmployeeVO> getListWithPaging(Criteria cri);
	
	public int getTotalCount(Criteria cri);
	
	
}
