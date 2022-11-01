package com.prj.lms.service.post;


import com.prj.lms.domain.post.PostEntity;
import com.prj.lms.domain.post.PostEntityEditor;
import com.prj.lms.exception.PostNotFound;
import com.prj.lms.repository.post.PostRepository;
import com.prj.lms.requestDto.post.PostCreate;
import com.prj.lms.requestDto.post.PostEdit;
import com.prj.lms.requestDto.post.PostSearch;
import com.prj.lms.responseDto.post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor // final 필드인 경우 @RequiredArgsConstructor사용가능
public class PostService {

    //@Autowired //필드 인젝션은 테스트 제외 권장하지 않음
    private final PostRepository postRepository;

    /*public PostService(PostRepository postRepository) { // 생성자를 구현해도 됨
        this.postRepository = postRepository;
    }*/

    public void write(PostCreate postCreate) {
        PostEntity postEntity = PostEntity.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        postRepository.save(postEntity);
    }

    public PostResponseDto get(Long id) {
        PostEntity postEntity = postRepository.findById(id) // Post엔티티를 바로 반환하는 것이 아닌, Optional 데이터로 감싸서 반환해줌.
                //.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
                .orElseThrow(PostNotFound::new);
        return PostResponseDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .build();
    }

    // 글이 많은 경우 -> 비용이 너무 많이 든다.
    // 글이 -> 100,000,000 -> DB 모두 조회하는 경우 -> DB가 뻗을 수 있다.
    // DB -> 애플리케이션 서버로 전달되는 시간, 트래필 비용등이 많이 발생할 수 있다.

    /*public List<PostResponse> getList(Pageable pageable) { //엔티티 바로 리턴하지 않고 PostResponse로 가공 리턴
        return postRepository.findAll(pageable).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }*/
    // QueryDsl 사용하여 직접 페이징 설정
    public List<PostResponseDto> getList(PostSearch postSearch) { //엔티티 바로 리턴하지 않고 PostResponse로 가공 리턴
        return postRepository.getList(postSearch).stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);
        PostEntityEditor.PostEntityEditorBuilder editorBuilder = postEntity.toEditor();
        /*PostEditor postEditor = editorBuilder.title(postEdit.getTitle())  // 수정값 중 null이 있는 경우 처리
                .content(postEdit.getContent())
                .build();*/
        if (postEdit.getTitle() != null) {
            editorBuilder.title(postEdit.getTitle());
        }
        if (postEdit.getContent() != null) {
            editorBuilder.content(postEdit.getContent());
        }
        postEntity.edit(editorBuilder.build());
        //postRepository.save(post); ==> save안하고 @Transactional붙이면 알아서 커밋!!!
    }

    public void delete(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);
        postRepository.delete(postEntity);
    }
}
