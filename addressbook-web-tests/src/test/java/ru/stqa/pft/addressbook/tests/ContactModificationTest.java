package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.goTo().groupPage();
            if (app.db().groups().size() == 0) {
                app.group().create(new GroupData().withName("test3").withHeader("test4").withFooter("test5"));
            }
            String a = app.contact().text();
            app.goTo().gotoNewContact();
            Groups groups = app.db().groups();
            app.contact().create(new ContactData()
                    .withName("Polina").withLastName("Kharchenko").withNickName("Polly").withMobilePhone("+71111111111").withAddress("drezden").withEmail("polly@mail.ru").inGroup(groups.iterator().next()), true);
        }
    }

    @Test

    public void testContactModification() {
        app.goTo().homePage();
        Contacts beforeCont = app.db().contacts();
        ContactData modifiedContact = beforeCont.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withName("Dima")
                .withLastName("Kharchenko")
                .withNickName("Dim")
                .withMobilePhone("111")
                .withHomePhone(null)
                .withWorkPhone("333")
                .withEmail("polly@mail.ru")
                .withAddress("USA");
        app.goTo().homePage();
        app.contact().modify(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(beforeCont.size()));
        Contacts afterCont = app.db().contacts();
        assertThat(afterCont, equalTo(beforeCont.withhout(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}
