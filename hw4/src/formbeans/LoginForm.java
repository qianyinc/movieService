/**
 * @author Qianying Chen Date:2016/12/13 
 * andrewId:qianyinc
 * course number:08-672
 */
package formbeans;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
    private String userName;
    private String password;
    
  
    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName.trim();
    }

    public String getPassword()  { return password; }
    
    public void setPassword(String s) { password = s.trim();}

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (userName == null || userName.length() == 0) {
            errors.add("user name is required");
        }
        
        if (password == null || password.length() == 0) {
            errors.add("Password is required");
        }
        
        return errors;
    }
}
