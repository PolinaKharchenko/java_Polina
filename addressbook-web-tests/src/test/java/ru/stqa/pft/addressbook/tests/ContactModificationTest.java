package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.goTo().groupPage();
            if (app.group().list().size() == 0) {
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

        List<ContactData> beforeCont = app.contact().list();
        int index = beforeCont.size() - 1;
        ContactData contact = new ContactData().withId(beforeCont.get(index).getId()).withName("Dima").withLastName("Kharchenko").withNickName("Dim").withTelephone("+71111111111").withEmail("polly@mail.ru");

        app.contact().modify(index, contact);
        List<ContactData> afterCont = app.contact().list();
        Assert.assertEquals(beforeCont.size(), afterCont.size());

        beforeCont.remove(index);
        beforeCont.add(contact);
        Comparator<? super ContactData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        beforeCont.sort(byID);
        afterCont.sort(byID);
        Assert.assertEquals(beforeCont, afterCont);
    }


}
