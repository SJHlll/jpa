package com.study.jpa.chap05_practice.dto;


import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListResponseDTO {

    private int count;
    private List<PostDetailResponseDTO> posts; // 게시물 렌더링 정보
    private pageResponseDTO pageInfo; // 페이지 렌더링 정보

}
