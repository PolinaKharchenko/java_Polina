package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ContactCreationTests extends TestBase {

   @Test
  public void testContactCreation() throws Exception {
       app.getNavigationHelper().gotoHome();
  //     List<ContactData> beforeCont = app.getContactHelper().getContactList();
       int beforeCont = app.getContactHelper().getContactCount();
       app.getNavigationHelper().goToGroupPage();
    if(! app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData("test3", "test4", "test5"));
    }
     String a = app.getContactHelper().text();
     System.out.println(a);;
    app.getNavigationHelper().gotoNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Polina", "Kharchenko", "Polly", "+71111111111", "polly@mail.ru", a), true);
       app.getNavigationHelper().gotoHome();
    //   List<ContactData> afterCont = app.getContactHelper().getContactList();
       int afterCont = app.getContactHelper().getContactCount();
       Assert.assertEquals(beforeCont, afterCont-1);
    //   Assert.assertEquals(beforeCont.size(), afterCont.size()-1);
   }



}
