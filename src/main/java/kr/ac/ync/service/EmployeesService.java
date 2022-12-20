package kr.ac.ync.service;

import java.util.List;

import kr.ac.ync.domain.Criteria;
import kr.ac.ync.domain.EmployeeVO;

public interface EmployeesService {
	public List<EmployeeVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
}
