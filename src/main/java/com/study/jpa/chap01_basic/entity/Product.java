package com.study.jpa.chap01_basic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity @Table(name = "tbl_product")
public class Product {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "prod_id") // 데이터베이스 컬럼명, 필수는 아님
    private Long id;

    @Column(name = "prod_name", nullable = false, length = 30) // NOT NULL, VARCHAR(30)
    private String name;

    private int price;

    @Enumerated(EnumType.STRING) // String 객체로, ORDINAL 은 0, 1, 2로 됨
    private Category category;

    @Column(updatable = false) // 만든 이후 수정 불가능
    @CreationTimestamp // DEFAULT_CURRENT_TIMESTAMP
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    public enum Category {
        FOOD, FASHION, ELECTRONIC
    }

}
