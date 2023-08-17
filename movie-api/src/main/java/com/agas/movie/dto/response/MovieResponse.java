package com.agas.movie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private Integer id;
    private String title;
    private String description;
    private Float rating;
    private String image;
    private String createdAt;
    private String modifiedAt;
}
