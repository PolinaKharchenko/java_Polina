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

public class AddContactToGroup extends TestBase{


      @BeforeMethod
    public void ensurePrecondition(){
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
    public void addContactToGroup(){
        app.goTo().homePage();
        Contacts contactAll = app.db().contacts();

        

        for(ContactData contact : contactAll){
               Integer selectgroups = app.contact().contactInGroup(contact);
               if(selectgroups ==0)
               {selectgroups = app.db().groups().iterator().next().getId();}
               app.contact().selectElementById(contact.getId());
               app.contact().toGroupList();
               app.contact().selectGroup(selectgroups);
               app.contact().addToGroup();
               break;
        }

        verifyContactListInUI();

    }







    }



