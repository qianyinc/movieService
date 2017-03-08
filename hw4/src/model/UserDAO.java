package model;
import java.util.Arrays;

import org.genericdao.ConnectionPool;

import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
//The user DAO will need to provide a read() method takes a user's e-mail address 
//and returns a UserBean containing information about that user. 
//The user DAO also provides a create() method that creates a new user record in the "user" table.
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.MovieBean;
import databeans.UserBean;

public class UserDAO extends GenericDAO<UserBean> {
    
    public UserDAO(String tableName, ConnectionPool pool) throws DAOException {
        super(UserBean.class, tableName, pool);
    }
    public UserBean[] getUsers() throws RollbackException {
        UserBean[] users = match();
        return users;
    }
    
    public UserBean read1(String userName) throws RollbackException  {
        UserBean[] list = match(MatchArg.equals("userName", userName));
        if(list == null || list.length==0){
            return null;
        }
        else {
            return list[0];
        }
 }
        
    
    
    public UserBean setPassword(String userName, String password) throws RollbackException {
        try {
            Transaction.begin();
            UserBean dbUser = read1(userName);

            if (dbUser == null) {
                throw new RollbackException("UserName " + userName + " does not exist");
            }

            dbUser.encodePassword(password);

            update(dbUser);
            Transaction.commit();
            
            return dbUser;
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }
    
}

