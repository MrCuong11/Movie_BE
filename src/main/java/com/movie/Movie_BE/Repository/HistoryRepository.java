package com.movie.Movie_BE.Repository;

import com.movie.Movie_BE.Model.History;
import com.movie.Movie_BE.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    Page<History> findByUser(User user, Pageable pageable);
}
