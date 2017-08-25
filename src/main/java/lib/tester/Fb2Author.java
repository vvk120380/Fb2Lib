package lib.tester;

/**
 * Created by md.sar.pc6 on 25.08.2017.
 */
public class Fb2Author {
    String first_name;
    String middle_name;
    String last_name;
    String nickname;
    String home_page;
    String email;

    public Fb2Author() {
    }

    public Fb2Author(String first_name, String middle_name, String last_name){
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHome_page() {
        return home_page;
    }

    public void setHome_page(String home_page) {
        this.home_page = home_page;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return first_name + ' ' + middle_name + ' ' + last_name;
    }
}
