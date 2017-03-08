package model;

import javax.servlet.ServletConfig;

import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databeans.MovieBean;
import databeans.UserBean;

public class Model {
    private MovieDAO movieDAO;
    private UserDAO  userDAO;

    public Model(ServletConfig config) throws ServletException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL    = config.getInitParameter("jdbcURL");
            
            ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
            userDAO  = new UserDAO("user", pool);
            movieDAO = new MovieDAO("movie",pool);
            try{
            if(userDAO.getUsers().length==0){
            UserBean u1 = new UserBean();
            UserBean u2 = new UserBean();
            u1.setUserName("qianyinc");
            u1.setFirstName("Qianying");
            u1.setLastName("Chen");
            u1.setHashedPassword("123");
            u1.setSalt(123);
            u1.setPassword("123");
            u1.setUserId(1);
            
            u2.setUserName("yuhanz");
            u2.setFirstName("YuHan");
            u2.setLastName("Zhou");
            u2.setHashedPassword("1");
            u2.setPassword("123");
            u2.setSalt(1);
            u2.setUserId(2);
            
           MovieBean m1 = new MovieBean();
           MovieBean m2 = new MovieBean();
           MovieBean m3 = new MovieBean();
           m1.setId(1);
           m1.setName("Movie A");
           m1.setRatings(4.2);
           m1.setUserId(1);
           m1.setDescription("Romantic Movie");
           m2.setId(2);
           m2.setName("Movie B");
           m2.setRatings(4.8);
           m2.setUserId(1);
           m2.setDescription("Documentary");
           m3.setId(3);
           m3.setName("Movie C");
           m3.setRatings(5.0);
           m3.setUserId(2);
           m3.setDescription("Science Fiction");
            userDAO.create(u1);
            userDAO.create(u2);
            
           movieDAO.create(m1);
            movieDAO.create(m2);
            movieDAO.create(m3);
          
            
        }}catch (RollbackException e) {
            e.printStackTrace();
        }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
    
    public MovieDAO getFavoriteDAO() { return movieDAO; }
    public UserDAO  getUserDAO()  { return userDAO;  }
}

