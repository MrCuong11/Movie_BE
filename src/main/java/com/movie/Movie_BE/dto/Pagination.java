package com.movie.Movie_BE.dto;

import com.movie.Movie_BE.Model.Film;
import org.springframework.data.domain.Page;

public class Pagination {
    private final long totalItems;
    private final int totalItemsPerPage;
    private final int currentPage;
    private final int totalPages;

    public Pagination(Page<Film> films) {
        this.totalItems = films.getTotalElements();
        this.totalItemsPerPage = films.getSize();
        this.currentPage = films.getNumber();
        this.totalPages = films.getTotalPages();
    }

    public long getTotalItems() {
        return totalItems;
    }

    public int getTotalItemsPerPage() {
        return totalItemsPerPage;
    }

    public int getCurrentPage() {
        return currentPage + 1;
    }

    public int getTotalPages() {
        return totalPages;
    }
}