package com.healthcare.enrollment.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.healthcare.enrollment.data.TestDataUtils;
import com.healthcare.enrollment.dto.DependentVO;
import com.healthcare.enrollment.dto.EnrolleeVO;
import com.healthcare.enrollment.entity.Dependent;
import com.healthcare.enrollment.entity.Enrollee;
import com.healthcare.enrollment.repository.DependentRepository;
import com.healthcare.enrollment.repository.EnrolleeRepository;
import com.healthcare.enrollment.util.Converter;

@RunWith(MockitoJUnitRunner.class)
public class EnrolleeServiceImplTest {
	
	@InjectMocks
	private EnrolleeServiceImpl enrolleeService;
	
	@Mock
	private EnrolleeRepository enrolleeRepository;
	
	@Mock
	private DependentRepository dependentRepository;
	
	private Converter converter = new Converter();
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(enrolleeService, "converter", converter);
    }
	
	@Test
	public void testGetEnrollee() {
		when(enrolleeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TestDataUtils.getEnrollee()));
		EnrolleeVO enrollee = enrolleeService.getEnrollee(1L);
		assertEquals("John",enrollee.getName());
		assertEquals(true, enrollee.isActivationStatus());
		
	}
	
	@Test
	public void testAddEnrollee() {
		Enrollee enrollee = TestDataUtils.getEnrollee();
		when(enrolleeRepository.save(Mockito.any())).thenReturn(enrollee);
		when(dependentRepository.saveAll(Mockito.any())).thenReturn(enrollee.getDependents());
		
		EnrolleeVO enrolleeVO =  enrolleeService.addEnrollee((EnrolleeVO)converter.convert(enrollee, EnrolleeVO.class));
		assertEquals("John",enrolleeVO.getName());
		assertEquals(1, enrolleeVO.getDependents().size());
		
	}
	
	@Test
	public void testUpdateEnrollee() {
		Enrollee enrollee = TestDataUtils.getEnrollee();
		when(enrolleeRepository.save(Mockito.any())).thenReturn(enrollee);
		when(dependentRepository.saveAll(Mockito.any())).thenReturn(enrollee.getDependents());
		
		EnrolleeVO enrolleeVO =  enrolleeService.updateEnrollee(1L,(EnrolleeVO)converter.convert(enrollee, EnrolleeVO.class));
		assertEquals("John",enrolleeVO.getName());
		assertEquals(1, enrolleeVO.getDependents().size());
		
	}

	@Test
	public void testDeleteEnrollee() {
		when(enrolleeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TestDataUtils.getEnrollee()));
		doNothing().when(dependentRepository).deleteAll(Mockito.any());
		doNothing().when(enrolleeRepository).deleteById(Mockito.any());
		String status = enrolleeService.deleteEnrollee(1L);
		assertEquals("deleted",status);
		
	}
	
	@Test
	public void testAddDependent() {
		when(enrolleeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TestDataUtils.getEnrollee()));
		Enrollee enrollee = TestDataUtils.getEnrollee();
		List<Dependent> dependents = enrollee.getDependents();
		when(enrolleeRepository.save(Mockito.any())).thenReturn(enrollee);
		//when(dependentRepository.saveAll(Mockito.any())).thenReturn(dependents);
		EnrolleeVO enrolleeVO = enrolleeService.addDependent(2L, (DependentVO)converter.convert(dependents.get(0), DependentVO.class));
		assertEquals("John",enrolleeVO.getName());
		
	}
	
	@Test
	public void testUpdateDependent() {
		when(enrolleeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TestDataUtils.getEnrollee()));
		Enrollee enrollee = TestDataUtils.getEnrollee();
		List<Dependent> dependents = enrollee.getDependents();
		when(enrolleeRepository.save(Mockito.any())).thenReturn(enrollee);
		//when(dependentRepository.saveAll(Mockito.any())).thenReturn(dependents);
		EnrolleeVO enrolleeVO = enrolleeService.updateDependent(2L, (DependentVO)converter.convert(dependents.get(0), DependentVO.class));
		assertEquals("John",enrolleeVO.getName());
		
	}
	
	@Test
	public void testDeleteDependents() {
		when(enrolleeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TestDataUtils.getEnrollee()));
		when(enrolleeRepository.save(Mockito.any())).thenReturn(TestDataUtils.getEnrollee());
		EnrolleeVO enrolleeVO = enrolleeService.removeDependent(1L);
		assertEquals("John",enrolleeVO.getName());
		
	}
}
