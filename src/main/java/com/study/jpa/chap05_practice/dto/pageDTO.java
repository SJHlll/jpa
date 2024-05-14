package com.study.jpa.chap05_practice.dto;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class pageDTO {

    private int page;
    private int size;

    public pageDTO() {
        this.page = 1;
        this.size = 10;
    }
}
