package controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.genericdao.RollbackException;
import databeans.MovieBean;
import databeans.UserBean;
import model.MovieDAO;
import model.Model;
import model.UserDAO;

public class ManageAction extends Action {

    private MovieDAO favDAO;
    private UserDAO  userDAO;

    public ManageAction(Model model) {
        favDAO = model.getFavoriteDAO();
        userDAO  = model.getUserDAO();
    }

    public String getName() { return "manage.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());
            UserBean user = (UserBean) request.getSession(false).getAttribute("user");
            //**
            if(user!=null){
            
            MovieBean[] favoriteList = favDAO.getUserFavorites(user);
            request.setAttribute("favoriteList",favoriteList);
            return "manage.jsp";
          //**
        }} catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
        //**
        return "login.jsp";
        //**
    }
}