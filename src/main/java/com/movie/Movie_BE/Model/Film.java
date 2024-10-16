package com.movie.Movie_BE.Model;
import jakarta.persistence.*;
import lombok.Data; // Import Lombok

@Data
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String slug;
    private String origin_name;
    private String poster_url;
    private String thumb_url;
    private int year;

    @Embedded
    private Modified modified = new Modified();
}
