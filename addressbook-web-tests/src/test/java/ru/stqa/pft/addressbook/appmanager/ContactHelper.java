package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.*;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void create(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        // type(By.name("mobile"), contactData.getTelephone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());
        //type(By.name("group"), contactData.getGroup());
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        click(By.xpath("//div[@id='content']/form/input[21]"));
        contactCache = null;
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void modify(ContactData contact) {
        selectElementById(contact.getId());
        initContactModificationByID(contact.getId());
        create(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectElementById(contact.getId());
        deleteCreateContact();
        contactCache = null;
        returnToHomePage();
    }

    public void deleteCreateContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElements(By.cssSelector("div.msgbox"));
    }

    public void selectElementById(int id) {
        wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }


    public String text() {
        String a = wd.findElement(By.cssSelector("span.group")).getText();
        return a;
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String lastName = String.valueOf(element.findElement(By.xpath(".//td[2]")).getText());
            String firstName = String.valueOf(element.findElement(By.xpath(".//td[3]")).getText());
            String allPhones = String.valueOf(element.findElement(By.xpath(".//td[6]")).getText());
            String allEmails = String.valueOf(element.findElement(By.xpath(".//td[5]")).getText());
            String address = String.valueOf(element.findElement(By.xpath(".//td[4]")).getText());
            //String[] phones = allPhones.split("\n");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //System.out.println(id);
            contactCache.add(new ContactData().withId(id).withName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationByID(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        //   File photo = new File(wd.findElement(By.name("photo")).getAttribute("value"));
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void initContactModificationByID(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

    }

    public void toGroupList() {
        click(By.name("to_group"));
    }

    public void addToGroup() {
        click(By.name("add"));
    }

    public void selectGroup(int i) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(i));
    }


    public void selectGroupFromList(int i) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(i));
    }

    public void selectContactById(int id) {
        wd.findElement(By.id(String.format("%s", id))).click();
    }

    public void deleteContactFromGroup() {
        wd.findElement(By.name("remove")).click();
    }



    public int addContactInGroup(ContactData contact, Groups groups){
        int groupID = 0;
        List<WebElement> options = wd.findElements(By.xpath("//select[@name='to_group']/option"));
        selectContactById(contact.getId());
        click(By.name("to_group"));
        for(WebElement option:options){
            boolean isInGroup = false;
            for(GroupData group:groups){
                if(Integer.parseInt(option.getAttribute("value"))==group.getId()){
                    isInGroup=true;
                }
            }
            if(!isInGroup){
                groupID = Integer.parseInt(option.getAttribute("value"));
                new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(option.getText());
                click(By.name("add"));
                break;
            }
        }
        return groupID;
    }
}
