package com.movie.Movie_BE.Service;

import com.movie.Movie_BE.Model.Category;
import com.movie.Movie_BE.Model.Country;
import com.movie.Movie_BE.Model.Episode;
import com.movie.Movie_BE.Model.Film;
import com.movie.Movie_BE.Repository.CategoryRepository;
import com.movie.Movie_BE.Repository.CountryRepository;
import com.movie.Movie_BE.Repository.FilmRepository;
import com.movie.Movie_BE.dto.FilmDTO;
import com.movie.Movie_BE.dto.FilmSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CountryRepository countryRepository;

    public Page<FilmSummary> getLatestFilms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Film> filmPage = filmRepository.findAll(pageable);

        List<FilmSummary> filmSummaries = filmPage.getContent().stream()
                .map(film -> {
                    FilmSummary summary = new FilmSummary();
                    summary.setId(film.getId());
                    summary.setName(film.getName());
                    summary.setSlug(film.getSlug());
                    summary.setOrigin_name(film.getOrigin_name());
                    summary.setPoster_url(film.getPoster_url());
                    summary.setThumb_url(film.getThumb_url());
                    summary.setYear(film.getYear());
                    return summary;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(filmSummaries, pageable, filmPage.getTotalElements());
    }

    public Film getFilmDetailBySlug(String slug) {
        return filmRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Film not found with slug: " + slug));
    }

    public Film createFilm(FilmDTO filmDTO) {
        if (filmRepository.existsBySlug(filmDTO.getSlug())) {
            throw new IllegalArgumentException("Slug đã tồn tại!");
        }


        Film film = new Film();

        film.setName(filmDTO.getName());
        film.setSlug(filmDTO.getSlug());
        film.setOrigin_name(filmDTO.getOrigin_name());
        film.setPoster_url(filmDTO.getPoster_url());
        film.setThumb_url(filmDTO.getThumb_url());
        film.setYear(filmDTO.getYear());
        film.setContent(filmDTO.getContent());
        film.setStatus(filmDTO.getStatus());
        film.setTime(filmDTO.getTime());
        film.setEpisode_current(filmDTO.getEpisode_current());
        film.setEpisode_total(filmDTO.getEpisode_total());
        film.setView(filmDTO.getView());
        film.setActor(filmDTO.getActor());
        film.setDirector(filmDTO.getDirector());


        if (filmDTO.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(filmDTO.getCategoryIds());
            film.setCategories(categories);
        }

        if (filmDTO.getCountryIds() != null) {
            List<Country> countries = countryRepository.findAllById(filmDTO.getCountryIds());
            film.setCountries(countries);
        }

        if (filmDTO.getEpisodes() != null) {
            List<Episode> episodes = filmDTO.getEpisodes().stream()
                    .map(episodeDTO -> {
                        Episode episode = new Episode();
                        episode.setId(episodeDTO.getId());
                        episode.setName(episodeDTO.getName());
                        episode.setSlug(episodeDTO.getSlug());
                        episode.setFilename(episodeDTO.getFilename());
                        episode.setLink_embed(episodeDTO.getLink_embed());
                        episode.setLink_m3u8(episodeDTO.getLink_m3u8());
                        episode.setFilm(film);
                        return episode;
                    })
                    .collect(Collectors.toList());
            film.setEpisodes(episodes);
        }

        return filmRepository.save(film);
    }





    public Film updateFilm(Long id, Film filmDetails) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found with id: " + id));

        // Cập nhật thông tin cho phim nếu có giá trị
        if (filmDetails.getName() != null) {
            film.setName(filmDetails.getName());
        }
        if (filmDetails.getSlug() != null) {
            film.setSlug(filmDetails.getSlug());
        }
        if (filmDetails.getOrigin_name() != null) {
            film.setOrigin_name(filmDetails.getOrigin_name());
        }
        if (filmDetails.getPoster_url() != null) {
            film.setPoster_url(filmDetails.getPoster_url());
        }
        if (filmDetails.getThumb_url() != null) {
            film.setThumb_url(filmDetails.getThumb_url());
        }
        if (filmDetails.getYear() != 0) {
            film.setYear(filmDetails.getYear());
        }
        if (filmDetails.getContent() != null) {
            film.setContent(filmDetails.getContent());
        }
        if (filmDetails.getStatus() != null) {
            film.setStatus(filmDetails.getStatus());
        }
        if (filmDetails.getTime() != null) {
            film.setTime(filmDetails.getTime());
        }
        if (filmDetails.getEpisode_current() != null) {
            film.setEpisode_current(filmDetails.getEpisode_current());
        }
        if (filmDetails.getEpisode_total() != null) {
            film.setEpisode_total(filmDetails.getEpisode_total());
        }
        if (filmDetails.getView() != 0) {
            film.setView(filmDetails.getView());
        }
        if (filmDetails.getActor() != null) {
            film.setActor(filmDetails.getActor());
        }
        if (filmDetails.getDirector() != null) {
            film.setDirector(filmDetails.getDirector());
        }

        return filmRepository.save(film);
    }


    public void deleteFilm(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found with id: " + id));
        filmRepository.delete(film);
    }



}
