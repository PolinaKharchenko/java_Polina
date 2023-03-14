package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ContactModificationTest extends TestBase{

  @Test

  public void testContactModification(){

    app.getNavigationHelper().gotoHome();
      if(! app.getContactHelper().isThereAContact())
    {   app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAGroup())
    {
        app.getGroupHelper().createGroup(new GroupData("test3", "test4", "test5"));}
        String a = app.getContactHelper().text();
        app.getNavigationHelper().gotoNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Polina", "Kharchenko", "Polly", "+71111111111", "polly@mail.ru", a), true);}
    app.getNavigationHelper().gotoHome();
    List<ContactData> beforeCont = app.getContactHelper().getContactList();
    app.getContactHelper().selectElement(beforeCont.size() - 1);
    app.getContactHelper().initContactModification(beforeCont.size() - 1);
    ContactData contact = new ContactData(beforeCont.get(beforeCont.size() - 1).getId(), "Dima", "Kharchenko", "Dim", "+71111111111", "polly@mail.ru", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHome();
    List<ContactData> afterCont = app.getContactHelper().getContactList();
    Assert.assertEquals(beforeCont.size(), afterCont.size());

   beforeCont.remove(beforeCont.size() - 1);
   beforeCont.add(contact);
      Comparator<? super ContactData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
      beforeCont.sort(byID);
      afterCont.sort(byID);
   Assert.assertEquals(beforeCont,afterCont);
  }

}
