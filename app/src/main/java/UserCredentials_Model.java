public class UserCredentials_Model
{
    String user_id,user_fname, user_lname, user_street, user_apt_num, user_city, user_state, user_postalcode;

    String user_contact,user_gender, user_email, user_displayname,user_password;


    public UserCredentials_Model(String user_id, String user_fname, String user_lname,
                                 String user_street, String user_apt_num, String user_city,
                                 String user_state, String user_postalcode, String user_contact,
                                 String user_gender, String user_email, String user_displayname, String user_password) {
        this.user_id = user_id;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_street = user_street;
        this.user_apt_num = user_apt_num;
        this.user_city = user_city;
        this.user_state = user_state;
        this.user_postalcode = user_postalcode;
        this.user_contact = user_contact;
        this.user_gender = user_gender;
        this.user_email = user_email;
        this.user_displayname = user_displayname;
        this.user_password = user_password;
    }

    public UserCredentials_Model(String user_id, String user_email, String user_password) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public String getUser_street() {
        return user_street;
    }

    public void setUser_street(String user_street) {
        this.user_street = user_street;
    }

    public String getUser_apt_num() {
        return user_apt_num;
    }

    public void setUser_apt_num(String user_apt_num) {
        this.user_apt_num = user_apt_num;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_state() {
        return user_state;
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    public String getUser_postalcode() {
        return user_postalcode;
    }

    public void setUser_postalcode(String user_postalcode) {
        this.user_postalcode = user_postalcode;
    }

    public String getUser_contact() {
        return user_contact;
    }

    public void setUser_contact(String user_contact) {
        this.user_contact = user_contact;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_displayname() {
        return user_displayname;
    }

    public void setUser_displayname(String user_displayname) {
        this.user_displayname = user_displayname;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString() {
        return "UserCredentials_Model{" +
                "user_id='" + user_id + '\'' +
                ", user_fname='" + user_fname + '\'' +
                ", user_lname='" + user_lname + '\'' +
                ", user_street='" + user_street + '\'' +
                ", user_apt_num='" + user_apt_num + '\'' +
                ", user_city='" + user_city + '\'' +
                ", user_state='" + user_state + '\'' +
                ", user_postalcode='" + user_postalcode + '\'' +
                ", user_contact='" + user_contact + '\'' +
                ", user_gender='" + user_gender + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_displayname='" + user_displayname + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }
}
