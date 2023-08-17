package com.agas.movie.domain;

import com.agas.movie.domain.baseentity.BaseEntity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="movie_table")
@SuperBuilder
@Getter
@Setter
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true, nullable = false,length = 50)
    private Integer id;

    @Column(name = "title",nullable = false,length = 200)
    private String title;
    @Column(name = "description",nullable = false,length = 200)
    private String description;
    @Column(name = "rating",length = 200)
    private Float rating;
    @Column(name = "image",length = 200)
    private String image;


}
