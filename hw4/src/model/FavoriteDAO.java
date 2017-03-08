/**
 * @author Qianying Chen Date:2016/12/13 
 * andrewId:qianyinc
 * course number:08-672
 */
package model;
import java.util.Arrays;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.MovieBean;

import databeans.UserBean;

public class FavoriteDAO extends GenericDAO<MovieBean> {
    
    public FavoriteDAO(String tableName, ConnectionPool pool) throws DAOException, org.genericdao.DAOException {
      super(MovieBean.class,tableName,pool);  
    }
    
    
    public void create(MovieBean favorite) throws RollbackException {
        try {
            Transaction.begin();
            MovieBean[] list = match(MatchArg.equals("userId", favorite.getUserId()));
            super.create(favorite);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }
    
    
//    public FavoriteBean read1(String url) throws RollbackException  {
//        FavoriteBean[] list = match(MatchArg.equals("url", url));
//        if(list == null || list.length==0){
//            return null;
//        }
//        else {
//            return list[0];
//        }
// }
    
    
    
  
    public void delete(int id, UserBean user) throws RollbackException {
        try {
            Transaction.begin();
             MovieBean fav = read(id);
           
            if(user==null){
                throw new RollbackException("Please login or register first.");
            }
            if (fav == null) {
                throw new RollbackException("Favorite URL doesn't exist");
            }
            
            if (user.getUserId()!=fav.getUserId()) {
                throw new RollbackException("The favorite URL not owned by you");
            }
            delete(id);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }
    public MovieBean[] getUserFavorites(UserBean user) throws RollbackException {
        //***
        if(user==null){
            throw new RollbackException("Please login or register first");
        }
        //***
       MovieBean[] items = match(MatchArg.equals("userId", user.getUserId()));
       return items;
    }
    
    public int clickAddOne(int id) throws DAOException, RollbackException{
        try {
            Transaction.begin();
            MovieBean fav = read(id);
            fav.setCount(fav.getCount()+1);
            update(fav);
            Transaction.commit();
        return fav.getCount();    
    }finally {
        if (Transaction.isActive()) Transaction.rollback();
    }
}
}

