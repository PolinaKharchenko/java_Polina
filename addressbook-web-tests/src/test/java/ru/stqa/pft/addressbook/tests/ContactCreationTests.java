package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import org.openqa.selenium.WebDriver;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

   @Test
  public void testContactCreation() throws Exception {
       app.getNavigationHelper().gotoHome();
     List<ContactData> beforeCont = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToGroupPage();
    if(! app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData("test3", "test4", "test5"));
    }
     String a = app.getContactHelper().text();
     System.out.println(a);;
    app.getNavigationHelper().gotoNewContact();
    ContactData contact = new ContactData("Polinaaa", "Kharchenko", "Polly", "+71111111111", "polly@mail.ru", a);
    app.getContactHelper().fillContactForm(contact, true);
       app.getNavigationHelper().gotoHome();
      List<ContactData> afterCont = app.getContactHelper().getContactList();
      Assert.assertEquals(beforeCont.size(), afterCont.size()-1);

       beforeCont.add(contact);
       Comparator<? super ContactData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
       beforeCont.sort(byID);
       afterCont.sort(byID);
      Assert.assertEquals(beforeCont, afterCont);

   }



}
