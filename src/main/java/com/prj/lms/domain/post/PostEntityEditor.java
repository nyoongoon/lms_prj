package com.prj.lms.domain.post;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostEntityEditor { //수정할 수 있는 필드들에 대해서만 정의
    private final String title;
    private final String content;

    @Builder
    public PostEntityEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public class PostEditorBuilder {
    }
}