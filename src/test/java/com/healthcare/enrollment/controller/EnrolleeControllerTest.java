package com.healthcare.enrollment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.enrollment.data.TestDataUtils;
import com.healthcare.enrollment.dto.DependentVO;
import com.healthcare.enrollment.dto.EnrolleeVO;
import com.healthcare.enrollment.service.EnrolleeService;
import com.healthcare.enrollment.util.Converter;

@WebMvcTest(EnrollmentController.class)
@RunWith(SpringRunner.class)
public class EnrolleeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EnrolleeService enrolleeService;
	
	private Converter converter = new Converter();
	
	private EnrolleeVO enrolleeVO = TestDataUtils.getEnrolleeVO();
	
	private DependentVO dependentVO = TestDataUtils.getDependentVO();

	private static ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testGetEnrollee() throws Exception {
		when(enrolleeService.getEnrollee(Mockito.anyLong())).thenReturn(enrolleeVO);
		this.mockMvc.perform(get("/healthcare/enrollment/enrollee/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name",is("John")));
	}
	
	@Test
	public void testAddEnrollee() throws Exception {
		
		when(enrolleeService.addEnrollee(Mockito.any())).thenReturn(enrolleeVO);
		String json = mapper.writeValueAsString(enrolleeVO);
		this.mockMvc.perform(post("/healthcare/enrollment/enroll").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name",is("John")));
	}
	
	@Test
	public void testUpdateEnrollee() throws Exception {
		
		when(enrolleeService.updateEnrollee(Mockito.anyLong(), Mockito.any())).thenReturn(enrolleeVO);
		String json = mapper.writeValueAsString(enrolleeVO);
		this.mockMvc.perform(put("/healthcare/enrollment/enrollee/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name",is("John")));
	}
	
	@Test
	public void testDeleteEnrollee() throws Exception {
		when(enrolleeService.deleteEnrollee(Mockito.anyLong())).thenReturn("deleted");
		this.mockMvc.perform(delete("/healthcare/enrollment/enrollee/1")).andExpect(status().isOk());
	}
	
	@Test
	public void testAddDependent() throws Exception {
		
		when(enrolleeService.addDependent(Mockito.anyLong(), Mockito.any())).thenReturn(enrolleeVO);		
		String json = mapper.writeValueAsString(dependentVO);
		this.mockMvc.perform(post("/healthcare/enrollment/dependent/3").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name",is("John")));
	}
	
	@Test
	public void testupdateDependent() throws Exception {
		
		when(enrolleeService.updateDependent(Mockito.anyLong(), Mockito.any())).thenReturn(enrolleeVO);		
		String json = mapper.writeValueAsString(dependentVO);
		this.mockMvc.perform(put("/healthcare/enrollment/dependent/3").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name",is("John")));
	}
	
	@Test
	public void testDeleteDependent() throws Exception {
		when(enrolleeService.removeDependent(Mockito.anyLong())).thenReturn(enrolleeVO);
		this.mockMvc.perform(delete("/healthcare/enrollment/dependent/3")).andExpect(status().isOk());
	}
	
	
}
