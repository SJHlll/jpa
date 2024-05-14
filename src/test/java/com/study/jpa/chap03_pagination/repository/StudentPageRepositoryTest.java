package com.study.jpa.chap03_pagination.repository;

import com.study.jpa.chap03_pagination.entity.Student2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
// JPA는 INSERT, UPDATE, DELETE시에 트랜잭션을 기준으로 동작하는 경우가 많음.
// 기능을 보장받기 위해서는 웬만하면 트랜잭션 기능을 함께 사용해야 합니다.
// 나중에 MVC 구조에서 Service 클래스에 아노테이션을 첨부하면 됩니다.
@Transactional
@Rollback(false)
class StudentPageRepositoryTest {
    
    @Autowired
    StudentPageRepository studentPageRepository;

    @Test
    void bulkInsert() {
        // 학생 더미 데이터를 저장
        for (int i = 1; i <= 147; i++) {
            Student2 s = Student2.builder()
                    .name("아무개" + i)
                    .city("어딘가" + i)
                    .major("무언가" + i)
                    .build();
            studentPageRepository.save(s);
        }
    }

    @Test
    @DisplayName("기본 페이징 테스트")
    void testBasicPagination() {
        // given
        int pageNo = 1;
        int amount = 10;

        // 페이지 정보 생성
        // 페이지 번호가 zero-based -> 0이 1페이지
        Pageable pageable =
                PageRequest.of(pageNo-1, amount,
                    Sort.by(
                            Sort.Order.desc("name"),
                            Sort.Order.asc("city")
                    )
                );

        // when
        Page<Student2> students = studentPageRepository.findAll(pageable);

        // 페이징이 적용된 총 학생 데이터 묶음
        List<Student2> studentList = students.getContent();

        int totalPages = students.getTotalPages();
        long totalElements = students.getTotalElements();
        boolean next = students.hasNext();
        boolean prev = students.hasPrevious();

        // then
        System.out.println("\n\n\n");
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("prev = " + prev);
        System.out.println("next = " + next);
        studentList.forEach(System.out::println);
        System.out.println("\n\n\n");

    }

    @Test
    @DisplayName("이름 검색 + 페이징")
    void testSearchAndPaging() {
        // given
        int pageNo = 5;
        int size = 9;
        Pageable pageInfo = PageRequest.of(pageNo, size);
        // when
        Page<Student2> students
                = studentPageRepository.findByNameContaining("3", pageInfo);

        int totalPages = students.getTotalPages();
        long totalElements = students.getTotalElements();
        boolean next = students.hasNext();
        boolean prev = students.hasPrevious();

         /*
          페이징 처리 시에 버튼 알고리즘은 jpa에서 따로 제공하지 않기 때문에
          버튼 배치 알고리즘을 수행할 클래스는 여전히 필요합니다.
          제공되는 정보는 이전보다 많기 때문에, 좀 더 수월하게 처리가 가능합니다.
         */

        // then
        System.out.println("\n\n\n");
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("prev = " + prev);
        System.out.println("next = " + next);
        students.getContent().forEach(System.out::println);
        System.out.println("\n\n\n");
    }

}


























