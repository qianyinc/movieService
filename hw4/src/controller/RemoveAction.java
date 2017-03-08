/**
 * @author Qianying Chen Date:2016/12/13 
 * andrewId:qianyinc
 * course number:08-672
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.MovieBean;
import databeans.UserBean;
import formbeans.IdForm;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;



public class RemoveAction extends Action {
    private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

    private FavoriteDAO favDAO;
    private UserDAO  userDAO;

    public RemoveAction(Model model) {
        favDAO = model.getFavoriteDAO();
        userDAO  = model.getUserDAO();
    }

    public String getName() { return "remove.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            IdForm form = formBeanFactory.create(request);
            
            UserBean user = (UserBean) request.getSession().getAttribute("user");

            int id = form.getIdAsInt();
            favDAO.delete(id,user);
            // Be sure to get the favoriteList after the delete
            MovieBean[] favoriteList = favDAO.getUserFavorites(user);
            request.setAttribute("favoriteList",favoriteList);
            return "manage.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}

