package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactToGroup extends TestBase{
    ContactData contact;


      @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
          if (!app.contact().isThereAContact()) {
            app.goTo().groupPage();
            if (contact.getGroups().size()>=app.db().groups().size()||app.db().groups().size() == 0) {
                app.group().create(new GroupData().withName("test3").withHeader("test4").withFooter("test5"));
            }

          //  String a = app.contact().text();
            app.goTo().gotoNewContact();
            Groups groups = app.db().groups();
            app.contact().create(new ContactData()
                    .withName("Polina").withLastName("Kharchenko").withNickName("Polly").withMobilePhone("+71111111111").withAddress("drezden").withEmail("polly@mail.ru").inGroup(groups.iterator().next()), true);
        }
          contact =app.db().contacts().iterator().next();
    }
    @Test
    public void addContactToGroup(){
        app.goTo().homePage();
        Groups contactGroupsBefore = contact.getGroups();
        int idGroups = app.contact().addContactInGroup(contact, contactGroupsBefore);
        GroupData groupToAdd = app.db().group(idGroups);
        ContactData con = app.db().contact(contact.getId());
        Groups contactGroupsAfter = con.getGroups();

        assertThat(contactGroupsAfter.size(), equalTo(contactGroupsBefore.size() + 1));
        assertThat(contactGroupsAfter,equalTo(contactGroupsBefore.withAdded(groupToAdd)));





    }







    }



