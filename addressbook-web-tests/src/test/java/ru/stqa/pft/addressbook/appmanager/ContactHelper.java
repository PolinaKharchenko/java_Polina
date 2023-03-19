package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void create(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("mobile"), contactData.getTelephone());
        type(By.name("email"), contactData.getEmail());
        //type(By.name("group"), contactData.getGroup());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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
        initContactModification();
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

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;
      public Contacts all() {
          if(contactCache !=null){
              return new Contacts(contactCache);
          }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String lastName = String.valueOf(element.findElement(By.xpath(".//td[2]")).getText());
            String firstName = String.valueOf(element.findElement(By.xpath(".//td[3]")).getText());
            Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //System.out.println(id);
            contactCache.add(new ContactData().withId(id).withName(firstName).withLastName(lastName));
        }
        return new Contacts(contactCache);
    }


}
