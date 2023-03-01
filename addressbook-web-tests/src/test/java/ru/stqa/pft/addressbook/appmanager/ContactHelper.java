package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("mobile"), contactData.getTelephone());
    type(By.name("email"), contactData.getEmail());
    //type(By.name("group"), contactData.getGroup());
  if (creation){
   new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());}
    else {
    Assert.assertFalse(isElementPresent(By.name("new_group")));
  }
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }


  public void deleteCreateContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

public void findElement() {
   click(By.name("selected[]"));
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
}
