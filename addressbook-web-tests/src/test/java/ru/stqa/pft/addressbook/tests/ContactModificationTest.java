package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test3").withHeader("test4").withFooter("test5"));
            }
            String a = app.contact().text();
            app.goTo().gotoNewContact();
            app.contact().create(new ContactData()
                    .withName("Polina").withLastName("Kharchenko").withNickName("Polly").withTelephone("+71111111111").withEmail("polly@mail.ru").withGroup(a), true);
        }
    }

    @Test

    public void testContactModification() {

        app.goTo().homePage();

        Set<ContactData> beforeCont = app.contact().all();
        ContactData modifiedContact = beforeCont.iterator().next();
        ContactData contact = new ContactData().withId( modifiedContact.getId()).withName("Dima").withLastName("Kharchenko").withNickName("Dim").withTelephone("+71111111111").withEmail("polly@mail.ru");

        app.contact().modify(contact);
        Set<ContactData> afterCont = app.contact().all();
        Assert.assertEquals(beforeCont.size(), afterCont.size());

        beforeCont.remove(modifiedContact);
        beforeCont.add(contact);
        Assert.assertEquals(beforeCont, afterCont);
    }


}
