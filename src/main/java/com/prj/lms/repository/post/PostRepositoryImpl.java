package com.prj.lms.repository.post;

import com.prj.lms.domain.post.PostEntity;
import com.prj.lms.domain.post.QPostEntity;
import com.prj.lms.requestDto.post.PostSearch;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor //생성자 자동주입
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<PostEntity> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(QPostEntity.postEntity)
                .limit(postSearch.getSize())
                .offset((long)(postSearch.getOffSet()))// 의존성 줄이도록 수정했음
                .orderBy(QPostEntity.postEntity.id.desc())
                .fetch();
    }
}
