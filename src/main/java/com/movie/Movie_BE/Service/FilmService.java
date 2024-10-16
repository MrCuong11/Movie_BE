package com.movie.Movie_BE.Service;

import com.movie.Movie_BE.Model.Film;
import com.movie.Movie_BE.Repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public Page<Film> getLatestFilms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return filmRepository.findAll(pageable);
    }

    public Film createFilm(Film film) {
        film.getModified().setTime(LocalDateTime.now());
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film film) {
        Film existingFilm = filmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Film not found with id " + id));

        if (film.getName() != null) existingFilm.setName(film.getName());
        if (film.getSlug() != null) existingFilm.setSlug(film.getSlug());
        if (film.getOrigin_name() != null) existingFilm.setOrigin_name(film.getOrigin_name());
        if (film.getPoster_url() != null) existingFilm.setPoster_url(film.getPoster_url());
        if (film.getThumb_url() != null) existingFilm.setThumb_url(film.getThumb_url());
        if (film.getYear() != 0) existingFilm.setYear(film.getYear());


        existingFilm.getModified().setTime(LocalDateTime.now());

        return filmRepository.save(existingFilm);
    }

    public void deleteFilm(Long id) {
        Film existingFilm = filmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Film not found with id " + id));
        filmRepository.delete(existingFilm);
    }
}
