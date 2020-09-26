package com.healthcare.enrollment.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.enrollment.dto.DependentVO;
import com.healthcare.enrollment.dto.EnrolleeVO;
import com.healthcare.enrollment.entity.Dependent;
import com.healthcare.enrollment.entity.Enrollee;
import com.healthcare.enrollment.repository.DependentRepository;
import com.healthcare.enrollment.repository.EnrolleeRepository;
import com.healthcare.enrollment.util.Converter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EnrolleeServiceImpl implements EnrolleeService{

	@Autowired
	private Converter converter;

	@Autowired
	EnrolleeRepository enrolleeRepository;

	@Autowired
	DependentRepository dependentRepository;

	@Override
	@Transactional
	public EnrolleeVO getEnrollee(long id) {
		log.info("ServiceImpl - Enrollee request for Id:{}", id);
		Enrollee enrollee = enrolleeRepository.findById(id).orElse(null);
		if (enrollee != null) {
			enrollee.getDependents();
			return (EnrolleeVO) converter.convert(enrollee, EnrolleeVO.class);
		}
		return null;
	}
	
	@Override
	@Transactional
	public EnrolleeVO addEnrollee(EnrolleeVO enrollees) {
		log.info("ServiceImpl - Add Enrollee");
		Enrollee modal = (Enrollee) converter.convert(enrollees, Enrollee.class);
		dependentRepository.saveAll(modal.getDependents());
		enrolleeRepository.save(modal);
		return (EnrolleeVO) converter.convert(modal, EnrolleeVO.class);
	}

	@Override
	@Transactional
	public EnrolleeVO updateEnrollee(long id, EnrolleeVO enrollees) {
		log.info("ServiceImpl - Update Enrollee for Id:{}", id);
		Enrollee modal = (Enrollee) converter.convert(enrollees, Enrollee.class);
		if (null != modal.getDependents() && !modal.getDependents().isEmpty()) {
			dependentRepository.saveAll(modal.getDependents());
		}
		enrolleeRepository.save(modal);
		return (EnrolleeVO) converter.convert(modal, EnrolleeVO.class);
	}
	
	@Override
	@Transactional
	public String deleteEnrollee(long id) {
		log.info("ServiceImpl - Delete Enrollee with Id:{}", id);
		Optional<Enrollee> findById = enrolleeRepository.findById(id);
		if (findById.isPresent()) {
			List<Dependent> dependents = findById.get().getDependents();
			if (null != dependents && !dependents.isEmpty()) {
				dependentRepository.deleteAll(dependents);
			}
			enrolleeRepository.deleteById(id);
			return "deleted";
		}
			return null;
	}
	
	@Override
	@Transactional
	public EnrolleeVO addDependent(long id, DependentVO dependents) {
		log.info("ServiceImpl - Add Dependent");
		Optional<Enrollee> findById = enrolleeRepository.findById(id);
		Enrollee EnroleeModel = null;
		if (findById.isPresent()) {
			EnroleeModel = findById.get();
			dependentRepository.save((Dependent) converter.convert(dependents, Dependent.class));
			enrolleeRepository.save(EnroleeModel);
		}
		if (null != EnroleeModel) {
			return (EnrolleeVO) converter.convert(EnroleeModel, EnrolleeVO.class);
		}
		return null;
	}
	
	@Override
	@Transactional
	public EnrolleeVO updateDependent(long id, DependentVO dependents) {
		log.info("ServiceImpl - Update Dependent");
		Optional<Enrollee> findById = enrolleeRepository.findById(id);
		Enrollee EnroleeModel = null;
		if (findById.isPresent()) {
			EnroleeModel = findById.get();
			dependentRepository.save((Dependent) converter.convert(dependents, Dependent.class));
			enrolleeRepository.save(EnroleeModel);
		}
		if (null != EnroleeModel) {
			return (EnrolleeVO) converter.convert(EnroleeModel, EnrolleeVO.class);
		}
		return null;
	}
	
	@Override
	@Transactional
	public EnrolleeVO removeDependent(long id) {
		log.info("ServiceImpl - Remove Dependent");
		Optional<Enrollee> findById = enrolleeRepository.findById(id);
		Enrollee EnrolleeModel = null;
		if (findById.isPresent()) {
			EnrolleeModel = findById.get();
			EnrolleeModel.getDependents().clear();
			enrolleeRepository.save(EnrolleeModel);
		}
		if (null != EnrolleeModel) {
			return (EnrolleeVO) converter.convert(EnrolleeModel, EnrolleeVO.class);
		}
		return null;
	}
}
