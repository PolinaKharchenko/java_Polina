package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

  @Test

  public void testContactModification(){
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().findElement();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Dima", "Kharchenko", "Dim", "+71111111111", "polly@mail.ru"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHome();
  }

}
