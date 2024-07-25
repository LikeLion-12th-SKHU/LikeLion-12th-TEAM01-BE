package org.skhuton.fitpete.community.comment.domain.repository;

import org.skhuton.fitpete.community.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}