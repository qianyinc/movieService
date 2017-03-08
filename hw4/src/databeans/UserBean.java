/**
 * @author Qianying Chen Date:2016/12/13 
 * andrewId:qianyinc
 * course number:08-672
 */
package databeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.genericdao.PrimaryKey;

@PrimaryKey("userId")
public class UserBean {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String address;
    private String hashedPassword = "*";
    private int salt = 0;

    public boolean checkPassword(String password) {
        return hashedPassword.equals(hash(password));
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

   

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String s) {
        this.firstName = s;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String s) {
        this.lastName = s;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String s) {
        this.password = s;
    }

    public void encodePassword(String s) {
        salt = newSalt();
        hashedPassword = hash(s);
    }

    private String hash(String clearPassword) {
        if (salt == 0)
            return null;

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
        }

        String saltString = String.valueOf(salt);

        md.update(saltString.getBytes());
        md.update(clearPassword.getBytes());
        byte[] digestBytes = md.digest();

        // Format the digest as a String
        StringBuffer digestSB = new StringBuffer();
        for (int i = 0; i < digestBytes.length; i++) {
            int lowNibble = digestBytes[i] & 0x0f;
            int highNibble = (digestBytes[i] >> 4) & 0x0f;
            digestSB.append(Integer.toHexString(highNibble));
            digestSB.append(Integer.toHexString(lowNibble));
        }
        String digestStr = digestSB.toString();

        return digestStr;
    }

    private int newSalt() {
        Random random = new Random();
        return random.nextInt(8192) + 1; // salt cannot be zero
    }
}
