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

public class MovieDAO extends GenericDAO<MovieBean> {
    
    public MovieDAO(String tableName, ConnectionPool pool) throws DAOException, org.genericdao.DAOException {
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
    
    
}

