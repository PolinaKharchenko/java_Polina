package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.goTo().groupPage();
            if (!app.group().isThereAGroup()) {
                app.group().create(new GroupData().withName("test3").withHeader("test4").withFooter("test5"));
            }
            String a = app.contact().text();
            app.goTo().gotoNewContact();
            app.contact().create(new ContactData()
                    .withName("Polina").withLastName("Kharchenko").withNickName("Polly").withMobilePhone("+71111111111").withEmail("polly@mail.ru").withGroup(a), true);
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        app.goTo().homePage();
        Contacts beforeCont = app.db().contacts();
        ContactData deletedContact = beforeCont.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(beforeCont.size()-1));
        Contacts afterCont = app.db().contacts();
        assertThat(afterCont, equalTo(beforeCont.withhout(deletedContact)));
    }
}
