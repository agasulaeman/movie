package com.agas.movie.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private String title;
    private String description;
    private Float rating;
    private String image;
}
