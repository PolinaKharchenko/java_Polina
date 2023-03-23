package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts beforeCont = app.contact().all();
        app.goTo().groupPage();
        if (!app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("test3").withHeader("test4").withFooter("test5"));
        }
        String a = app.contact().text();
        app.goTo().gotoNewContact();
        File photo = new File("src/test/resources/1.jpg");
        ContactData contact = new ContactData()
                .withName("Polinaaa").withLastName("Kharchenko").withNickName("Polly").withMobilePhone("+71111111111").withEmail("polly@mail.ru").withPhoto(photo).withGroup(a);
        app.contact().create(contact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(beforeCont.size() + 1));
        Contacts afterCont = app.contact().all();
        assertThat(afterCont, equalTo(
                beforeCont.withAdded(contact.withId(afterCont.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsoluteFile());
        File photo = new File("src/test/resources/1.jpg");
        System.out.println(photo.getAbsoluteFile());
        System.out.println(photo.exists());
    }

    @Test
    public void testBadContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts beforeCont = app.contact().all();
        app.goTo().groupPage();
        if (!app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("test3").withHeader("test4").withFooter("test5"));
        }
        String a = app.contact().text();
        app.goTo().gotoNewContact();
        ContactData contact = new ContactData()
                .withName("Polinaaa'").withLastName("Kharchenko").withNickName("Polly").withMobilePhone("+71111111111").withEmail("polly@mail.ru").withGroup(a);
        app.contact().create(contact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(beforeCont.size()));
        Contacts afterCont = app.contact().all();
        assertThat(afterCont, equalTo(beforeCont));
    }
}
