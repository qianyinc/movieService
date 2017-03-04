package edu.cmu.mis.iccfb.controller;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import edu.cmu.mis.iccfb.model.Movie;
import edu.cmu.mis.iccfb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@EnableCircuitBreaker
@SpringBootApplication
@RestController

public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private DiscoveryClient discoveryClient;




    @RequestMapping(value = "/api/movie", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "fallback")
    public void saveMovie(@RequestBody Movie movie) {
        System.out.println(movie);
        RestTemplate restTemplate = new RestTemplate();
        movieService.save(movie);
    }

    @HystrixCommand(fallbackMethod = "listFallback")
    @RequestMapping(value = "/api/quote", method = RequestMethod.GET)
    public List<Movie> listMovies() {
        List<Movie> movies = new ArrayList<>();
        movieService.findAll().iterator().forEachRemaining(movies::add);
        return movies;
    }


    public Movie fallback() {
        Movie m = new Movie();
        m.setName("LA LA LAND");
        m.setDescription("Romantic Movie");
        m.setRatings(4.9);
        return m;
    }

    public List<Movie> listFallback() {
        List<Movie> list = new ArrayList<>();
        Movie m1 = new Movie();
        m1.setId(1l);
        m1.setDescription("romantic movie");
        m1.setName("LA LA LAND");
        m1.setRatings(4.9);
        list.add(m1);
        Movie m2 = new Movie();
        m1.setId(2l);
        m1.setDescription("science fiction");
        m1.setName("Transformer");
        m1.setRatings(4.5);
        list.add(m2);
        return list;
    }


}
