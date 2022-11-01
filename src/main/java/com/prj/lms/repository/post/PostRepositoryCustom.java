package com.prj.lms.repository.post;

import com.prj.lms.domain.post.PostEntity;
import com.prj.lms.requestDto.post.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostEntity> getList(PostSearch postSearch);
}
