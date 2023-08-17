package com.agas.movie.service;
import com.agas.movie.dto.request.MovieRequest;
import com.agas.movie.dto.response.MovieResponse;
import java.util.Map;

public interface MovieService {
    Map<String, Object> getAll();
    MovieResponse createMovie(MovieRequest request);
    Map<String,Object> updateMovieById (Integer id,MovieRequest request);
    Map<String, Object> delete(Integer id);
    Map<String,Object> findMovieById(Integer id);

}
