package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static ru.stqa.pft.mantis.tests.TestBase.app;

public class ChangePassword extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void ChangePassword() throws MessagingException, IOException {
        long now = System.currentTimeMillis();
String password =  String.format("passwordEdited%s", now);
app.passwordHelper().autorizationByAdmin();
app.passwordHelper().goToUserManagement();
User user = app.passwordHelper().getUser().iterator().next();


String passwordBefore = user.getPasswordEncurted();
app.controlHelper().resertPassword(user.getUserName());


        // String user = "user" + now;
        //String password = "password";
        //String email = String.format("user%s@localhost", now);
        //app.jamesHelper().createUser(user, password);

       // app.registration().start(user, email);
        //  List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        List<MailMessage> mailMessages = app.jamesHelper().waitForMail(user, password, 6000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    //  @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}


