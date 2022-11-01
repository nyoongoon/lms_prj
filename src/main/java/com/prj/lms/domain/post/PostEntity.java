package com.prj.lms.domain.post;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name="dpw_post")//다산패스웹
@NoArgsConstructor(access =  AccessLevel.PUBLIC) //파라미터가 없는 기본 생성자를 생성,
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @Builder
    public PostEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostEntityEditor.PostEntityEditorBuilder toEditor(){
        return PostEntityEditor.builder()
                .title(this.title)
                .content(this.content);
                //.build();   ==>빌더를 넘기는 것이기때문에 빌드 메소드를 사용하면 안됨 !!
    }

    public void edit(PostEntityEditor postEntityEditor) {
        this.title = postEntityEditor.getTitle();
        this.content = postEntityEditor.getContent();
    }
}
