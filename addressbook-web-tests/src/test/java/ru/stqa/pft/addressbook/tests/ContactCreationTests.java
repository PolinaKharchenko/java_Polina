package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        // List<Object[]> list = new ArrayList<>();
        app.goTo().groupPage();
        String groupName = "test1";
        if (!app.group().isThereAGroup(groupName)) {
            app.group().create(new GroupData().withName(groupName).withHeader("test4").withFooter("test5"));
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            //    String[] split = line.split(";");
            //    list.add(new Object[]{new ContactData().withName(split[0]).withLastName(split[1]).withNickName(split[2]).withAddress(split[3]).withEmail(split[4]).withGroup(groupName)});
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        xstream.allowTypes(new Class[]{ContactData.class});
        List<ContactData> groups = (List<ContactData>) xstream.fromXML(xml);
        return groups.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();}
        // list.add(new Object[] {new ContactData().withName("Polina3").withLastName("Kharchenko").withNickName("Polly3").withMobilePhone("444").withEmail("polly@mail.ru").withGroup(groupName)});
        // return list.iterator();
    }


    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        Groups groups = app.db().groups();
        Contacts beforeCont = app.db().contacts();
        app.goTo().gotoNewContact();
        File photo = new File("src/test/resources/1.jpg");
        app.contact().create(contact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(beforeCont.size() + 1));
        Contacts afterCont = app.db().contacts();
        assertThat(afterCont, equalTo(
                beforeCont.withAdded(contact.withId(afterCont.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }

    @Test
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsoluteFile());
        File photo = new File("src/test/resources/1.jpg");
        System.out.println(photo.getAbsoluteFile());
        System.out.println(photo.exists());
    }

    @Test
    public void testBadContactCreation() throws Exception {
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/1.jpg");
        ContactData newContact = new ContactData().withName("Polinaaa'").withLastName("Kharchenko").withNickName("Polly").withMobilePhone("+71111111111").withEmail("polly@mail.ru").withPhoto(photo).inGroup(groups.iterator().next());

    app.goTo().homePage();
        Contacts beforeCont = app.db().contacts();
        app.goTo().groupPage();
        if (!app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("test3").withHeader("test4").withFooter("test5"));
        }
        String a = app.contact().text();
        app.goTo().gotoNewContact();
        app.contact().create(newContact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(beforeCont.size()));
        Contacts afterCont = app.db().contacts();
        assertThat(afterCont, equalTo(beforeCont));
    }
}
