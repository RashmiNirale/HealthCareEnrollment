package com.healthcare.enrollment.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.healthcare.enrollment.dto.DependentVO;
import com.healthcare.enrollment.dto.EnrolleeVO;
import com.healthcare.enrollment.entity.Dependent;
import com.healthcare.enrollment.entity.Enrollee;
import com.healthcare.enrollment.util.Converter;

public class TestDataUtils {

	private TestDataUtils() {
		
	}
	
	static Converter converter = new Converter();
	
	public static Enrollee getEnrollee() {
		Enrollee enrollee = new Enrollee();
		enrollee.setId(1L);
		enrollee.setName("John");
		enrollee.setDob(new Date());
		enrollee.setPhoneNumber("123-929-7820");
		enrollee.setActivationStatus(true);
		enrollee.setDependents(getDependents());
		return enrollee;
	}
	
	public static Dependent getDependent() {
		Dependent dependent = new Dependent();
		dependent.setId(2L);
		dependent.setName("Carol");
		dependent.setDob(new Date());
		return dependent;
	}
	
	public static List<Dependent> getDependents(){
		List<Dependent> dependents = new ArrayList<>();
		dependents.add(getDependent());
		return dependents;
	}
	
	public static EnrolleeVO getEnrolleeVO() {
		return (EnrolleeVO) converter.convert(getEnrollee(), EnrolleeVO.class);
	}
	
	public static DependentVO getDependentVO() {
		return (DependentVO) converter.convert(getDependent(), DependentVO.class);
	}
}
