package com.movie.Movie_BE.Repository;
import com.movie.Movie_BE.Model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
}

