package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {

    app.getNavigationHelper().gotoNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Polina", "Kharchenko", "Polly", "+71111111111", "polly@mail.ru"));
      }

}
