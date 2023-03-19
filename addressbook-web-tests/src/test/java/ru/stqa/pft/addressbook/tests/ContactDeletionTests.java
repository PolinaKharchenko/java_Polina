package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

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
        List<ContactData> beforeCont = app.contact().list();
        int index = beforeCont.size() - 1;
        app.contact().delete(index);
        List<ContactData> afterCont = app.contact().list();
        Assert.assertEquals(beforeCont.size(), afterCont.size() + 1);

        beforeCont.remove(index);
        Assert.assertEquals(beforeCont, afterCont);

    }

}
