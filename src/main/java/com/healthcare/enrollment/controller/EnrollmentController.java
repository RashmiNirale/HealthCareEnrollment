package com.healthcare.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.enrollment.dto.DependentVO;
import com.healthcare.enrollment.dto.EnrolleeVO;
import com.healthcare.enrollment.service.EnrolleeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/healthcare/enrollment")
@Api(value = "EnrollmentController", tags = "Healthcare Enrollment")
@Slf4j
public class EnrollmentController {

	@Autowired
	EnrolleeService enrolleeService;

	@ApiOperation(value = "Get the application Enrollee", tags = "Healthcare Enrollment")
	@GetMapping("/enrollee/{id}")
	public ResponseEntity<EnrolleeVO> getEnrollee(@PathVariable("id") Long id) {
		log.info("Enrollee request for Id:{}", id);
		return ResponseEntity.ok(enrolleeService.getEnrollee(id));
	}

	@PostMapping("/enroll")
	public ResponseEntity<EnrolleeVO> enroll(@RequestBody EnrolleeVO enroleeVO) {
		return ResponseEntity.ok(enrolleeService.addEnrollee(enroleeVO));
	}

	@PutMapping("/enrollee/{id}")
	public ResponseEntity<EnrolleeVO> update(@PathVariable("id") Long id, @RequestBody EnrolleeVO enroleeVO) {
		return ResponseEntity.ok(enrolleeService.updateEnrollee(id, enroleeVO));
	}

	@DeleteMapping("/enrollee/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		String response = enrolleeService.deleteEnrollee(id);
		if (null != response) {
			return ResponseEntity.ok("deleted");
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping("/dependent/{id}")
	// @ApiOperation(value = "Create Dependent", notes = "This method creates a new
	// Dependent")
	public ResponseEntity<EnrolleeVO> addDependent(@PathVariable("id") long id, @RequestBody DependentVO depenVo) {
		EnrolleeVO enrollees = enrolleeService.addDependent(id, depenVo);
		if (null != enrollees) {
			return ResponseEntity.ok(enrollees);
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/dependent/{id}")
	// @ApiOperation(value = "Create Dependent", notes = "This method creates a new
	// Dependent")
	public ResponseEntity<EnrolleeVO> updateDependent(@PathVariable("id") long id,
			@RequestBody DependentVO depenVo) {
		EnrolleeVO enrollees = enrolleeService.updateDependent(id, depenVo);
		if (null != enrollees) {
			return ResponseEntity.ok(enrollees);
		}
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/dependent/{id}")
	public ResponseEntity<EnrolleeVO> removeDependent(@PathVariable("id") long id) {
		EnrolleeVO enrollees = enrolleeService.removeDependent(id);
		if (null != enrollees) {
			return ResponseEntity.ok(enrollees);
		}
		return ResponseEntity.noContent().build();
	}
}
