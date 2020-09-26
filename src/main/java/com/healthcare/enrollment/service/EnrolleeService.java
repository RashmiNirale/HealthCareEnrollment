package com.healthcare.enrollment.service;

import com.healthcare.enrollment.dto.DependentVO;
import com.healthcare.enrollment.dto.EnrolleeVO;

public interface EnrolleeService {

	EnrolleeVO getEnrollee(long id);
	EnrolleeVO addEnrollee(EnrolleeVO enrollees);
	EnrolleeVO updateEnrollee(long id, EnrolleeVO enrollees);
	String deleteEnrollee(long id);
	EnrolleeVO addDependent(long id, DependentVO dependents);
	EnrolleeVO removeDependent(long id);
	EnrolleeVO updateDependent(long id, DependentVO dependents);

}
