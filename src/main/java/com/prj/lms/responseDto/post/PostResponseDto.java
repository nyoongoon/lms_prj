package com.prj.lms.responseDto.post;

import com.prj.lms.domain.post.PostEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    public PostResponseDto(PostEntity postEntity){
        this.id = postEntity.getId();
        this.title = postEntity.getTitle();
        this.content = postEntity.getContent();
    }
    @Builder
    public PostResponseDto(Long id, String title, String content){
        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 10));
        this.content = content;
    }
}
