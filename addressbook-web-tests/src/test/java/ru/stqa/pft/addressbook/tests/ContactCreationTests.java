package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ContactCreationTests extends TestBase {

   @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if(! app.getGroupHelper().isThereAGroup()) {
     app.getGroupHelper().createGroup(new GroupData(null,"test3", "test4", "test5"));
    }
    //List<GroupData> before = app.getGroupHelper().getGroupList();
    //String i = String.valueOf(before.get(0));
      // System.out.println(i);
    app.getNavigationHelper().gotoNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Polina", "Kharchenko", "Polly", "+71111111111", "polly@mail.ru", "test3"), true);
      }

}
