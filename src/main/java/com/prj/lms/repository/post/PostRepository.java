package com.prj.lms.repository.post;

import com.prj.lms.domain.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long>, PostRepositoryCustom {

}
