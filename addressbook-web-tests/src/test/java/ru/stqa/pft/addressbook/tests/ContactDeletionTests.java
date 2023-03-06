package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {

    app.getNavigationHelper().gotoHome();
    if(! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToGroupPage();
      if(! app.getGroupHelper().isThereAGroup()) {
              app.getGroupHelper().createGroup(new GroupData(null, "test3", "test4", "test5"));
      }
      app.getNavigationHelper().gotoNewContact();
      app.getContactHelper().fillContactForm(new ContactData("Polina", "Kharchenko", "Polly", "+71111111111", "polly@mail.ru", "test3"), true);
    }
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().findElement();
    app.getContactHelper().deleteCreateContact();
  }


}
