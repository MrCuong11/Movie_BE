package com.movie.Movie_BE.Repository;
import com.movie.Movie_BE.Model.Film;
import com.movie.Movie_BE.dto.FilmSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findBySlug(String slug);
    boolean existsBySlug(String slug);
    boolean existsById(Long id);
}


