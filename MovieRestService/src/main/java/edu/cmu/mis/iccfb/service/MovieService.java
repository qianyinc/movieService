package edu.cmu.mis.iccfb.service;

import java.util.List;

import edu.cmu.mis.iccfb.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface MovieService extends CrudRepository<Movie, Long>{
    List<Movie> findByRating(String name);
    Movie findByName(String name);


}
