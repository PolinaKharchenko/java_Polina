package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTest extends TestBase{

  @Test

  public void testContactModification(){

    app.getNavigationHelper().gotoHome();
      int beforeCont = app.getContactHelper().getContactCount();
    if(! app.getContactHelper().isThereAContact())
    {   app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAGroup())
    {
        app.getGroupHelper().createGroup(new GroupData("test3", "test4", "test5"));}

        app.getNavigationHelper().gotoNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Polina", "Kharchenko", "Polly", "+71111111111", "polly@mail.ru", "test3"), true);}

    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectElement(beforeCont - 1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Dima", "Kharchenko", "Dim", "+71111111111", "polly@mail.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHome();
      int afterCont = app.getContactHelper().getContactCount();
      Assert.assertEquals(beforeCont, afterCont);
  }

}
