/**
 * @author Qianying Chen Date:2016/12/13 
 * andrewId:qianyinc
 * course number:08-672
 */
package model;

import javax.servlet.ServletConfig;

import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databeans.MovieBean;
import databeans.UserBean;

public class Model {
    private FavoriteDAO favDAO;
    private UserDAO  userDAO;

    public Model(ServletConfig config) throws ServletException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL    = config.getInitParameter("jdbcURL");
            
            ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
            userDAO  = new UserDAO("qianyinc_user", pool);
            favDAO = new FavoriteDAO("qianyinc_favorite",pool);
            try{
            if(userDAO.getUsers().length==0){
            UserBean u1 = new UserBean();
            UserBean u2 = new UserBean();
            UserBean u3 = new UserBean();
            MovieBean fav1 = new MovieBean();
            MovieBean fav2 = new MovieBean();
            MovieBean fav3 = new MovieBean();
            MovieBean fav4 = new MovieBean();
            MovieBean fav5 = new MovieBean();
            MovieBean fav6 = new MovieBean();
            MovieBean fav7 = new MovieBean();
            MovieBean fav8 = new MovieBean();
            MovieBean fav9 = new MovieBean();
            MovieBean fav10 = new MovieBean();
            MovieBean fav11 = new MovieBean();
            MovieBean fav12 = new MovieBean();
            u1.setEmail("qianyinc@andrew.cmu.edu");
            u1.setFirstName("Qianying");
            u1.setLastName("Chen");
            u1.setHashedPassword("123");
            u1.setSalt(123);
            u1.setPassword("123");
            u1.setUserId(1);
            
            u2.setEmail("cqy0118@hotmail.com");
            u2.setFirstName("Yueqiao");
            u2.setLastName("Wang");
            u2.setHashedPassword("1");
            u2.setPassword("123");
            u2.setSalt(1);
            u2.setUserId(2);
            
            u3.setEmail("cqy0118@gmail.com");
            u3.setFirstName("Mingjia");
            u3.setLastName("Liu");
            u3.setHashedPassword("1");
            u3.setPassword("123");
            u3.setSalt(1);
            u3.setUserId(3);
            
            
            fav1.setCount(0);
            fav1.setComment("google");
            fav1.setId(1);
            fav1.setUserId(1);
            fav1.setURL("www.google.com");
            
            fav2.setCount(0);
            fav2.setComment("baidu");
            fav2.setId(2);
            fav2.setUserId(1);
            fav2.setURL("www.baidu.com");
            
            fav3.setCount(0);
            fav3.setComment("sina");
            fav3.setId(3);
            fav3.setUserId(1);
            fav3.setURL("www.sina.com");
            
            fav4.setCount(0);
            fav4.setComment("hotmail");
            fav4.setId(4);
            fav4.setUserId(1);
            fav4.setURL("www.hotmail.com");
            
            fav5.setCount(0);
            fav5.setComment("hotmail");
            fav5.setId(5);
            fav5.setUserId(2);
            fav5.setURL("www.hotmail.com");
            
            fav6.setCount(0);
            fav6.setComment("facebook");
            fav6.setId(6);
            fav6.setUserId(2);
            fav6.setURL("www.facebook.com");
            
            fav7.setCount(0);
            fav7.setComment("baidu");
            fav7.setId(7);
            fav7.setUserId(2);
            fav7.setURL("www.baidu.com");
            
            fav8.setCount(0);
            fav8.setComment("google");
            fav8.setId(8);
            fav8.setUserId(2);
            fav8.setURL("www.google.com");
            
            fav9.setCount(0);
            fav9.setComment("zhihu");
            fav9.setId(9);
            fav9.setUserId(3);
            fav9.setURL("www.zhihu.com");
            
            fav10.setCount(0);
            fav10.setComment("baidu");
            fav10.setId(10);
            fav10.setUserId(3);
            fav10.setURL("www.baidu.com");
            
            fav11.setCount(0);
            fav11.setComment("google");
            fav11.setId(11);
            fav11.setUserId(3);
            fav11.setURL("www.google.com");
            
            fav12.setCount(0);
            fav12.setComment("facebook");
            fav12.setId(12);
            fav12.setUserId(3);
            fav12.setURL("www.facebook.com");
            
            userDAO.create(u1);
            userDAO.create(u2);
            userDAO.create(u3);
            favDAO.create(fav1);
            favDAO.create(fav2);
            favDAO.create(fav3);
            favDAO.create(fav4);
            favDAO.create(fav5);
            favDAO.create(fav6);
            favDAO.create(fav7);
            favDAO.create(fav8);
            favDAO.create(fav9);
            favDAO.create(fav10);
            favDAO.create(fav11);
            favDAO.create(fav12);
            
        }}catch (RollbackException e) {
            e.printStackTrace();
        }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
    
    public FavoriteDAO getFavoriteDAO() { return favDAO; }
    public UserDAO  getUserDAO()  { return userDAO;  }
}

