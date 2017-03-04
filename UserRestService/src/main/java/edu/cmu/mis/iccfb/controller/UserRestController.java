package edu.cmu.mis.iccfb.controller;
import edu.cmu.mis.iccfb.model.User;
import edu.cmu.mis.iccfb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableCircuitBreaker
@RestController
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private DiscoveryClient discoveryClient;

//    @HystrixCommand(fallbackMethod = "findAuthorFallback")
//    @RequestMapping(value = "/api/author/{authorId}", method = RequestMethod.GET)
//    public User findByAuthorId(@RequestParam(required = true)Long id) {
//
//        return userService.findById(id);
//    }
//    @HystrixCommand(fallbackMethod = "findAuthorNameFallback")
//    @RequestMapping(value = "/api/author/by", method = RequestMethod.GET)
//    public User findByName(@RequestParam(required = true)String name) {
//
//        return userService.findByName(name);
//    }

    @HystrixCommand(fallbackMethod = "saveUserFallback")
    @RequestMapping(value = "/api/author", method = RequestMethod.POST)
    public Object saveAuthor(@RequestBody Long id, String firstName, String lastName, String userName, String password, String addresss) {
        User user = new User(id,firstName,lastName,userName,password,addresss);
            userService.save(user);
        return user;

    }

    public User saveUserFallback(){
        User u1 = new User(1l,"Qianying","Chen","qianyinc","123","Royal Garden Apt.");
        return u1;

    }




}
