package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.MovieBean;
import databeans.UserBean;
import formbeans.UserIdForm;
import model.MovieDAO;
import model.Model;
import model.UserDAO;

public class RecommendationAction extends Action {
    private FormBeanFactory<UserIdForm> formBeanFactory = FormBeanFactory
            .getInstance(UserIdForm.class);

    private MovieDAO movieDAO;
    private UserDAO userDAO;

    public RecommendationAction(Model model) {
        movieDAO = model.getFavoriteDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "recommendation.do";
    }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't
        // correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
       
        try {
            // Set up user list for nav bar
            request.setAttribute("userList", userDAO.getUsers());
            UserIdForm form = formBeanFactory.create(request);
            int userId = form.getUserIdAsInt();
            UserBean user = userDAO.read(userId);
            if (user == null) {
                errors.add("Invalid User Id: " + userId);
                return "error.jsp";
            }
            MovieBean[] favoriteList = movieDAO.getUserFavorites(user);
            request.setAttribute("favoriteList", favoriteList);
            return "recommendation.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}