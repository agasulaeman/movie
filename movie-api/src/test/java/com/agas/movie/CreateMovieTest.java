package com.agas.movie;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateMovieTest {

    @Test
    public void testMovieResponseDTOToJson() throws Exception {
        // Create a MovieResponseDTO object with the given data
        MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
        movieResponseDTO.setId(11);
        movieResponseDTO.setTitle("Bajaj Bajuri");
        movieResponseDTO.setDescription("Mat Solar");
        movieResponseDTO.setRating(77.01f);
        movieResponseDTO.setImage("wkwkwkwk");

        // Create SimpleDateFormat with the desired format
        /**
         *for this expect only response in swagger / postman
         */
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2023-08-17 20:58:26");

        // Set createdAt and modifiedAt using appropriate Date objects
        movieResponseDTO.setCreatedAt(date);
        movieResponseDTO.setModifiedAt(date);

        // Convert MovieResponseDTO to JSON using Gson
        String actualJson = new Gson().toJson(movieResponseDTO);

        // Expected JSON result
        String expectedJson = "{\"id\":11,\"title\":\"Bajaj Bajuri\",\"description\":\"Mat Solar\",\"rating\":77.01,\"image\":\"wkwkwkwk\",\"createdAt\":\"2023-08-17 20:58:26\",\"modifiedAt\":\"2023-08-17 20:58:26\"}";

        // Compare expected JSON with actual JSON
        assertEquals(expectedJson, actualJson);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class MovieResponseDTO {
        private Integer id;
        private String title;
        private String description;
        private Float rating;
        private String image;
        private Date createdAt;
        private Date modifiedAt;
    }
}
