package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

public class Welcome extends Action {
    private UserDAO userDAO;
    public Welcome(Model model) {
        userDAO = model.getUserDAO();
    }
    @Override
    public String getName() {
        return "welcome.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object user= session.getAttribute("user");
        if(user!=null) return "dashboard.do";
        return "login.jsp";
    }
   

}