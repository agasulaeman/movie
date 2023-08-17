package com.agas.movie.repository;

import com.agas.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    @Query(value="SELECT * FROM movie_table mt "
            + "WHERE mt.id = :id "
            + "AND mt.is_deleted = false", nativeQuery= true)
    Movie findByIdTrue(@Param("id") Integer id);
}
