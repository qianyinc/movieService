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
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.UserBean;
import formbeans.ChangePwdForm;

public class ChangePwdAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePwdForm.class);

	private UserDAO userDAO;

	public ChangePwdAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() {
		return "change-pwd.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// Set up user list for nav bar
			request.setAttribute("userList", userDAO.getUsers());

			// Load the form parameters into a form bean
			ChangePwdForm form = formBeanFactory.create(request);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "change-pwd.jsp";
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "change-pwd.jsp";
			}
			
			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("user");
        //看看这个setPassword是不是有问题！！！！！
			// Change the password
			UserBean updatedUser = userDAO.setPassword(user.getUserName(), form.getNewPassword());
			
			session.setAttribute("user", updatedUser);
			request.setAttribute("message", "Password changed for " + user.getFirstName()+" "+user.getLastName());
			return "success.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}
}
