/**
 * @author Qianying Chen Date:2016/12/13 
 * andrewId:qianyinc
 * course number:08-672
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class UserIdForm extends FormBean {
    private String userId;

    public String getUserId() { return userId;    }
    
    public int getUserIdAsInt() {
        try {
            return Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            // call getValidationErrors() to detect this
            return -1;
        }
    }
    public void setUserId(String s) { this.userId = s; }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        
        if (userId == null || userId.length() == 0) {
            errors.add("User Id is required");
            return errors;
        }

        try {
            Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            errors.add("User id is not an integer");
        }
        
        return errors;
    }
}
