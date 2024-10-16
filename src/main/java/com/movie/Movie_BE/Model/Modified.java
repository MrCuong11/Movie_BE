package com.movie.Movie_BE.Model;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;


@Embeddable
public class Modified {
    private LocalDateTime time;

    public void setCurrentTime() {
        this.time = LocalDateTime.now();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}


