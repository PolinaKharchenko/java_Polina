package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTest extends TestBase{

  @Test

  public void testContactModification(){

    app.getNavigationHelper().gotoHome();
    if(! app.getContactHelper().isThereAContact())
    { if(! app.getGroupHelper().isThereAGroup())
    {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test3", "test4", "test5"));}

        app.getNavigationHelper().gotoNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Polina", "Kharchenko", "Polly", "+71111111111", "polly@mail.ru", "test3"), true);}
    //else {
      //if(! app.getContactHelper().isThereAContact()) {
      //  app.getNavigationHelper().gotoNewContact();
      //  app.getContactHelper().fillContactForm(new ContactData("Polina", "Kharchenko", "Polly", "+71111111111", "polly@mail.ru", "test3"), true);}
      //}

    app.getNavigationHelper().gotoHome();
    app.getContactHelper().findElement();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Dima", "Kharchenko", "Dim", "+71111111111", "polly@mail.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHome();
  }

}
