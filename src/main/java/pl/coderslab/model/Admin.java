package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class Admin {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean superAdmin;
    private boolean enable;

    public Admin() {
    }

    public Admin(String firstName, String lastName, String email, String password, boolean superAdmin, boolean enable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPasswordWithHashing(password);
        this.superAdmin = superAdmin;
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Admin [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + ", superAdmin=" + superAdmin + ", enable=" + enable + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getPassword() {
        return password;
    }

    //set password from simple input to hashed password
    public void setPasswordWithHashing(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //set password from hashed input to hashed password
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPasswordCorrect(String password) {
        return BCrypt.checkpw(password, getPassword());
    }
}
