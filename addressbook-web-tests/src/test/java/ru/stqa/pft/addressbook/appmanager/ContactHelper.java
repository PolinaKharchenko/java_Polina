package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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
    wd.switchTo().alert().accept();
  }

public void selectElement(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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

   public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    for(WebElement element: elements)
    {
      String name = element.getText();
      ContactData contact = new ContactData(name, null,null, null,null,null);
      contacts.add(contact);
    }
    return contacts;
  }
}
