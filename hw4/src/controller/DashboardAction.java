package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.UserBean;
import formbeans.LoginForm;
import model.Model;
import model.UserDAO;

public class DashboardAction extends Action {
    private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
    private UserDAO userDAO;
    public DashboardAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() { return "dashboard.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        
        try {
            request.setAttribute("userList", userDAO.getUsers());
            LoginForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);
           
            // If no params were passed, return with no errors so that the form will be
            // presented (we assume for the first time).
            if (!form.isPresent()) {
                return "dashboard.jsp";
            }
           
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "dashboard.jsp";
            }
            
            // Look up the user
      
            UserBean user  = new UserBean();
            user = userDAO.read1(form.getUserName());
            
            
            if (user == null) {
                errors.add("User not found");
                return "dashboard.jsp";
            }

            // Check the password
            if (!user.checkPassword(form.getPassword())) {
                errors.add("Incorrect password");
                
                return "login.jsp";
            }
            // Attach (this copy of) the user bean to the session
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            return "recommendation.do";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
          
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}

