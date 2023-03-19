package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        List<ContactData> beforeCont = app.contact().list();

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
        List<ContactData> afterCont = app.contact().list();
        Assert.assertEquals(beforeCont.size(), afterCont.size() - 1);

        beforeCont.add(contact);
        Comparator<? super ContactData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        beforeCont.sort(byID);
        afterCont.sort(byID);
        Assert.assertEquals(beforeCont, afterCont);

    }


}
