package com.study.jpa.chap03_pagination.repository;

import com.study.jpa.chap03_pagination.entity.Student2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPageRepository extends JpaRepository<Student2, String> {

     // 학생 조건 없이 전체 조회 페이징 (기본기능 - 안만들어도 됨)
     Page<Student2> findAll(Pageable pageable);

     // 학생의 이름에 특정 단어가 포함된 걸 조회 + 페이징
    Page<Student2> findByNameContaining(String name, Pageable pageable);
}
