package edu.cmu.mis.iccfb.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private String addresss;

        public User(Long id, String firstName, String lastName, String userName, String password, String addresss) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.addresss = addresss;
    }
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getAddresss() {
            return addresss;
        }
        public void setAddresss(String addresss) {
            this.addresss = addresss;
        }
        @Override
        public String toString() {
            return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
                    + ", password=" + password + ", addresss=" + addresss + "]";
        }
    }

