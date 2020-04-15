package com.rmurugaian.spring.repository;

import com.rmurugaian.spring.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rmurugaian 2018-07-13
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    List<Student> findAll();

    List<Student> findByStatus(final String status);
}
