package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.name());
    type(By.name("lastname"), contactData.lastName());
    type(By.name("nickname"), contactData.nickName());
    type(By.name("mobile"), contactData.telephone());
    type(By.name("email"), contactData.email());
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void deleteCreateContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

public void findElement() {
   click(By.id("1"));
  }

}
