/**
 * @author Qianying Chen Date:2016/12/13 
 * andrewId:qianyinc
 * course number:08-672
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.MovieBean;
import formbeans.IdForm;

import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

public class ClickAction extends Action{
    private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory
            .getInstance(IdForm.class);
private FavoriteDAO favDAO;
private UserDAO userDAO;
public ClickAction(Model model) {
        favDAO = model.getFavoriteDAO(); 
        userDAO = model.getUserDAO();
}

    @Override
    public String getName() {
        return "click.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        try {
            request.setAttribute("userList", userDAO.getUsers());
            IdForm form = formBeanFactory.create(request);
            
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "error.jsp";
            }
                int id = form.getIdAsInt();
                MovieBean fav = favDAO.read(id);
            if (fav != null)
                favDAO.clickAddOne(fav.getId());
               return fav.getURL();
            } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (DAOException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
        }
    }

