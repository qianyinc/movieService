/**
 * @author Qianying Chen Date:2016/12/13 
 * andrewId:qianyinc
 * course number:08-672
 */
package formbeans;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;
public class RegisterForm extends FormBean{

private String password;
private String firstName;
private String lastName;
private String userName;
private String address;


public String getUserName() {
    return userName;
}
public void setUserName(String userName) {
    this.userName = userName;
}
public String getAddress() {
    return address;
}
public void setAddress(String address) {
    this.address = address;
}
public void setFirstName(String s) { firstName = trimAndConvert(s,"<>\"");  }
public void setLastName(String s)  { lastName  = trimAndConvert(s,"<>\"");  }

public void setPassword(String s)  { password  = s.trim();                  }




public String getPassword() {
    return password;
}
public String getFirstName() {
    return firstName;
}
public String getLastName() {
    return lastName;
}

public List<String> getValidationErrors() {
    List<String> errors = new ArrayList<String>();
    if (userName == null || userName.length() == 0)
        errors.add("email is required");
    if (password == null || password.length() == 0)
        errors.add("Password is required");
    if (firstName == null || firstName.length() == 0)
        errors.add("First Name is required");
    if (lastName == null || lastName.length() == 0)
        errors.add("Last Name is required");
    

    if (errors.size() > 0)
        return errors;


 
    return errors;
}

}
