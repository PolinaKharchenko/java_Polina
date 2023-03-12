package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
      app.getNavigationHelper().gotoHome();
    if(! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToGroupPage();
      if(! app.getGroupHelper().isThereAGroup()) {
              app.getGroupHelper().createGroup(new GroupData("test3", "test4", "test5"));
      }
      String a = app.getContactHelper().text();
      app.getNavigationHelper().gotoNewContact();
      app.getContactHelper().fillContactForm(new ContactData("Polina", "Kharchenko", "Polly", "+71111111111", "polly@mail.ru", a), true);
       }
      app.getNavigationHelper().gotoHome();
    List<ContactData> beforeCont = app.getContactHelper().getContactList();
    app.getContactHelper().selectElement( beforeCont.size()-1);
    app.getContactHelper().deleteCreateContact();
    app.getNavigationHelper().gotoHome();
    List<ContactData> afterCont = app.getContactHelper().getContactList();
   Assert.assertEquals(afterCont.size(), beforeCont.size()-1);

    beforeCont.remove(beforeCont.size() - 1);
    Assert.assertEquals(beforeCont, afterCont);
  }


}
