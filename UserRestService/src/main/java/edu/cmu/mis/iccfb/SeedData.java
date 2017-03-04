package edu.cmu.mis.iccfb;


import edu.cmu.mis.iccfb.model.User;
import edu.cmu.mis.iccfb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SeedData {

    @Autowired
    private UserService userService;
    

    
    private static final Logger log = LoggerFactory.getLogger(SeedData.class);

    @Bean
    public SeedData getBean() {
        //Long id, String firstName, String lastName, String userName, String password, String addresss
        User u1 = new User(1l,"Qianying","Chen","qianyinc","123","Royal Garden Apt.");
        User u2 = new User(2l,"Yuhan","Zhou","raina","123","Royal Garden Apt.");

        
        userService.save(u1);
        userService.save(u2);



        
        log.info("saving users:");
        log.info("-------------------------------");

        return new SeedData();
    }
}
