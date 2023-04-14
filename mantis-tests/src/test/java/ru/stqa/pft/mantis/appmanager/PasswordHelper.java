package ru.stqa.pft.mantis.appmanager;

import org.apache.http.client.methods.HttpPost;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PasswordHelper extends HelperBase {
   // protected WebDriver wd;
   // protected ApplicationManager app;

    public PasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void autorizationByAdmin(){

        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), "administrator");
        click(By.xpath("//input[@type='submit']"));
        type(By.name("password"), "root");
        click(By.xpath("//input[@type='submit']"));
    }

    public void changePassword(String username){
        click(By.linkText(username));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void goToUserManagement(){
        click(By.linkText("Управление"));
        click(By.linkText("Управление пользователями"));
    }

    public List<User> getUser(){
        Connection conn = null;
        List<User> user = new ArrayList<User>();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=user1&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, username, email, password from mantis_user_table");
            //User user = new User();
            while (rs.next()){
                user.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password")));
            }
            rs.close();
            st.close();
            conn.close();
          //  System.out.println(user);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return user;
    }
    public void getPassword(){
        click(By.linkText("Управление"));
        click(By.linkText("Управление пользователями"));
    }

    }

