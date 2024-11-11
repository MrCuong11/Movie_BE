package com.movie.Movie_BE.Repository;

import com.movie.Movie_BE.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
