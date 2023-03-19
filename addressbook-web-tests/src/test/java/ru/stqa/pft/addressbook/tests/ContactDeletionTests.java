package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

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
                    .withName("Polina").withLastName("Kharchenko").withNickName("Polly").withTelephone("+71111111111").withEmail("polly@mail.ru").withGroup(a), true);
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        app.goTo().homePage();
        Set<ContactData> beforeCont = app.contact().all();
        ContactData deleteContact = beforeCont.iterator().next();
        app.contact().delete(deleteContact);
        Set<ContactData> afterCont = app.contact().all();
        Assert.assertEquals(beforeCont.size(), afterCont.size() + 1);

        beforeCont.remove(deleteContact);
        Assert.assertEquals(beforeCont, afterCont);

    }

}
