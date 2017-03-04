package edu.cmu.mis.iccfb;
import edu.cmu.mis.iccfb.model.Movie;
import edu.cmu.mis.iccfb.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedData {


    
    @Autowired
    private MovieService movieService;


    private static final Logger log = LoggerFactory.getLogger(SeedData.class);

    @Bean
    public SeedData getBean() {
        Movie m1 = new Movie(1L,"LA LA LAND","romantic movie",4.9);
        Movie m2 = new Movie(2L,"Transformer","science fiction",4.5);
        Movie m3 = new Movie(3L,"Inception","science fiction",4.5);
        Movie m4 = new Movie(4L,"The Notebook","romantic movie",4.9);
        movieService.save(m1);
        movieService.save(m2);
        movieService.save(m3);
        movieService.save(m4);
        
        log.info("saving movies");
        log.info("-------------------------------");
        for (Movie m : movieService.findAll()) {
            log.info(m.toString());
        }
        return new SeedData();
    }
}
