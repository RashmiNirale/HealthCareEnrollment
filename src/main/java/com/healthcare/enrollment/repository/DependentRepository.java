package com.healthcare.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.enrollment.entity.Dependent;

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Long> {

}
