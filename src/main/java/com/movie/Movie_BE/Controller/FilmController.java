package com.movie.Movie_BE.Controller;

import com.movie.Movie_BE.Model.Film;
import com.movie.Movie_BE.Service.FilmService;
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
        Page<Film> films = filmService.getLatestFilms(page - 1, pageSize);
        return ResponseEntity.ok(createResponse(films));
    }

    // Tạo một phim mới
    @PostMapping("/phim")
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        Film createdFilm = filmService.createFilm(film);
        return ResponseEntity.status(201).body(createdFilm);
    }

    // Cập nhật một phim theo ID
    @PutMapping("/phim/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film film) {
        Film updatedFilm = filmService.updateFilm(id, film);
        return ResponseEntity.ok(updatedFilm);
    }

    // Xóa một phim theo ID
    @DeleteMapping("/phim/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build(); // Trả về 204 No Content
    }


    private Object createResponse(Page<Film> films) {
        return new Object() {
            public boolean status = films.hasContent();
            public List<Film> items = films.getContent();
            public Pagination pagination = new Pagination(films);
        };
    }
}
