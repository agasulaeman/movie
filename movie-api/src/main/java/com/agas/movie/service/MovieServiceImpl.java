package com.agas.movie.service;

import com.agas.movie.config.Constants;
import com.agas.movie.domain.Movie;
import com.agas.movie.dto.request.MovieRequest;
import com.agas.movie.dto.response.MovieResponse;
import com.agas.movie.repository.MovieRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    ModelMapper modelMapper = new ModelMapper();


    @Override
    public MovieResponse createMovie(MovieRequest movieRequest) {
        log.info("Processing add Movie {} {} => ");
        Movie movie = Movie.builder()
                .title(movieRequest.getTitle())
                .description(movieRequest.getDescription())
                .rating(movieRequest.getRating())
                .image(movieRequest.getImage())
                .deleted(false)
                .build();

        Movie saveMovie = movieRepository.save(movie);

       MovieResponse response = convertToMovieResponseDTO(saveMovie);
        return response;
    }

    @Override
    public Map<String, Object> getAll() {
        Map<String,Object> result = new HashMap<>();
        List<MovieResponse> listMovie = new ArrayList<>();

        for (Movie dataMovie: movieRepository.findAll()) {
            if (dataMovie.getDeleted().equals(false)) {
                MovieResponse response = convertToMovieResponseDTO(dataMovie);
                listMovie.add(response);
            }
        }
        String message = "";
        if (!listMovie.isEmpty()) {
            message = Constants.success_string;
        } else {
            message = Constants.empty_data_string;
        }

        result.put(Constants.response , HttpStatus.OK);
        result.put("message", message);
        result.put("data", listMovie);
        result.put("total", listMovie.size());
        return result;

    }

    @Override
    public Map<String, Object> findMovieById(Integer id) {
        Map<String,Object> result = new HashMap<>();
        String message = "";
        try {
            Movie movieData = movieRepository.findByIdTrue(id);
            MovieResponse responseMovie = convertToMovieResponseDTO(movieData);
            message = Constants.success_string;
            result.put("message", message);
            result.put(Constants.response, HttpStatus.OK);
            result.put("Data", responseMovie);

        } catch (Exception e) {
            result.put("error_code",HttpStatus.NOT_FOUND);
            result.put("message",Constants.movie_notfound);
        }
        return result;
    }

    @Override
    @SneakyThrows
    public Map<String, Object> updateMovieById(Integer id, MovieRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            Movie dataMovie = movieRepository.findByIdTrue(id);
            Movie movieIndex = convertToEntity(request);

            dataMovie.setTitle(movieIndex.getTitle());
            dataMovie.setRating(movieIndex.getRating());
            dataMovie.setImage(movieIndex.getImage());
            dataMovie.setDescription(movieIndex.getDescription());

            Movie resultInput = movieRepository.save(dataMovie);

            MovieResponse response =   convertToMovieResponseDTO(resultInput);

                    result.put(Constants.response, HttpStatus.OK);
            result.put("message", Constants.success_string);
            result.put("Data", response);
        } catch (Exception e) {
            result.put("error_code", HttpStatus.NOT_FOUND);
            result.put("message", Constants.movie_notfound);
        }
        return result;
    }

    @Override
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Movie data = movieRepository.findByIdTrue(id);
            data.setDeleted(true);

            Movie resultInput = movieRepository.save(data);
            MovieResponse response = convertToMovieResponseDTO(resultInput);
            result.put("Data", response);
            result.put("response", HttpStatus.OK);
            result.put("message", "Data Has been deleted");
        } catch (Exception e) {
            result.put(Constants.response, HttpStatus.OK);
            result.put(Constants.response, HttpStatus.NOT_FOUND);
        }
        return result;
    }

    private MovieResponse convertToMovieResponseDTO(Movie movie) {
        MovieResponse dto = new MovieResponse();
        LocalDateTime localDateCreateTime = movie.getCreatedAt().atZone(ZoneId.of("Asia/Bangkok")).toLocalDateTime();
        LocalDateTime localDateUpdateTime = movie.getModifiedAt().atZone(ZoneId.of("Asia/Bangkok")).toLocalDateTime();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createDate = localDateCreateTime.format(outputFormatter);
        String updateDate = localDateUpdateTime.format(outputFormatter);
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setRating(movie.getRating());
        dto.setImage(movie.getImage());
        dto.setCreatedAt(createDate);
        dto.setModifiedAt(updateDate);
        return dto;
    }
    private Movie convertToEntity (MovieRequest request) {
        return modelMapper.map(request, Movie.class);
    }

}

