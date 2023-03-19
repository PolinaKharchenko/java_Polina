package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Set<ContactData> beforeCont = app.contact().all();

        app.goTo().groupPage();
        if (!app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("test3").withHeader("test4").withFooter("test5"));
        }
        String a = app.contact().text();
        app.goTo().gotoNewContact();
        ContactData contact = new ContactData()
                .withName("Polinaaa").withLastName("Kharchenko").withNickName("Polly").withTelephone("+71111111111").withEmail("polly@mail.ru").withGroup(a);
        app.contact().create(contact, true);

        app.goTo().homePage();
        Set<ContactData> afterCont = app.contact().all();
        Assert.assertEquals(beforeCont.size(), afterCont.size() - 1);


        contact.withId(afterCont.stream().mapToInt((g) ->g.getId()).max().getAsInt());
        beforeCont.add(contact);
        Assert.assertEquals(beforeCont, afterCont);

    }


}
