package com.healthcare.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.enrollment.entity.Enrollee;

@Repository
public interface EnrolleeRepository extends JpaRepository<Enrollee, Long> {

}
