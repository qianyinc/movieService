/**
 * @author Qianying Chen 
 * Date:2016/12/13 
 * andrewId:qianyinc
 * course number:08-672
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import databeans.MovieBean;
import databeans.UserBean;

import org.genericdao.RollbackException;

import org.mybeans.form.FormBeanException;

import formbeans.FavoriteForm;

import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

public class AddAction extends Action {
    private FormBeanFactory<FavoriteForm> formBeanFactory = FormBeanFactory
            .getInstance(FavoriteForm.class);
    private FavoriteDAO favDAO;
    private UserDAO userDAO;
    public AddAction(Model model) {
        favDAO = model.getFavoriteDAO();
        userDAO = model.getUserDAO();
        
        
    }

    public String getName() {   
        return "add.do";
    }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
        try {
            request.setAttribute("userList", userDAO.getUsers());

            UserBean user = (UserBean) request.getSession(false).getAttribute("user");
            MovieBean[] favoriteList = favDAO.getUserFavorites(user);
            request.setAttribute("favoriteList", favoriteList);
            FavoriteForm form = formBeanFactory.create(request);
           
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0)
                return "error.jsp";      
            
            MovieBean fav = new MovieBean();
            if (form.getUrl().startsWith("www.")) {
                fav.setURL(form.getUrl());
            } else {
                errors.add("The URL is invalid, please enter a URL starts with 'www'.");
                return "error.jsp";
            }
            
            if (form.getComment().length() > 0) {
                fav.setComment(form.getComment());
            } else {
                errors.add("The comment can not be empty.");
            }
            fav.setUserId(user.getUserId());
            favDAO.create(fav);
            // Update photoList (there's now one more on the list)
            MovieBean[] list = favDAO.getUserFavorites(user);
            request.setAttribute("favoriteList", list);
            return "manage.do";
           // return "manage.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "manage.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "manage.jsp";
        }
    }
        
    }


