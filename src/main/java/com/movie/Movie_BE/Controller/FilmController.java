package com.movie.Movie_BE.Controller;

import com.movie.Movie_BE.Model.Film;
import com.movie.Movie_BE.Service.FilmService;
import com.movie.Movie_BE.dto.FilmDTO;
import com.movie.Movie_BE.dto.FilmSummary;
import com.movie.Movie_BE.dto.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/danh-sach")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/phim-moi-cap-nhat")
    public ResponseEntity<Object> getLatestFilms(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<FilmSummary> films = filmService.getLatestFilms(page - 1, pageSize);
        return ResponseEntity.ok(createResponse(films));
    }
    //get return status 200

    @GetMapping("/phim/{slug}")
    public ResponseEntity<Film> getFilmDetailBySlug(@PathVariable String slug) {
        Film film = filmService.getFilmDetailBySlug(slug);
        return ResponseEntity.ok(film);
    }


    @PostMapping("/phim")
    public ResponseEntity<Film> createFilm(@RequestBody FilmDTO film) {
        Film createdFilm = filmService.createFilm(film);
        return ResponseEntity.status(201).body(createdFilm);
    }
    //post return status 201
//
//
    @PutMapping("/phim/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film film) {
        Film updatedFilm = filmService.updateFilm(id, film);
        return ResponseEntity.ok(updatedFilm);
    }
//    //put return status 200 or 204
//
//
    @DeleteMapping("/phim/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
//    //delete return status 204 (No content)

    private Object createResponse(Page<FilmSummary> films) {
        return new Object() {
            public boolean status = films.hasContent();
            public List<FilmSummary> items = films.getContent();
            public Pagination pagination = new Pagination(films);
        };
    }

}
