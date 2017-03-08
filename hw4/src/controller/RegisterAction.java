package controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.UserBean;
import formbeans.RegisterForm;

public class RegisterAction extends Action{

    private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);

    private UserDAO userDAO;
    
    public RegisterAction(Model model) {
        userDAO = model.getUserDAO();
    }
    
    @Override
    public String getName() {
        return "register.do"; 
    }

    @Override
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        

        try {
            request.setAttribute("userList", userDAO.getUsers());
            RegisterForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);
    
            
            UserBean  checkUser = userDAO.read1(form.getUserName());
            if(checkUser!=null)
                errors.add("Username exist");
            
            // If no params were passed, return with no errors so that the form will be
            // presented (we assume for the first time).
           
            if (!form.isPresent()) {
              
                return "register.jsp";
            }
    
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "register.jsp";
            }
    
            // Create the user bean
            UserBean user = new UserBean();
            user.setAddress(form.getAddress());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setUserName(form.getUserName());
            user.encodePassword(form.getPassword());
            userDAO.create(user);
        
            // Attach (this copy of) the user bean to the session
            HttpSession session = request.getSession(false);
            session.setAttribute("user",user);
           
            return "dashboard.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "register.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "register.jsp";
        }
    }
}