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

    public void modifyContact(int index, ContactData contact) {
        selectElement(index);
       initContactModification(index);
       fillContactForm(contact, false);
       submitContactModification();
          }

  public void deleteCreateContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

public void selectElement(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactModification(int i) {
   wd.findElements(By.xpath("//img[@alt='Edit']")).get(i).click();
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
       List<WebElement> elements = wd.findElements(By.name("entry"));
    for(WebElement element: elements)
    {
      String lastName = String.valueOf(element.findElement(By.xpath(".//td[2]")).getText());
      String firstName = String.valueOf(element.findElement(By.xpath(".//td[3]")).getText());
      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      System.out.println(id);
      ContactData contact = new ContactData(id, firstName, lastName,null, null,null,null);
      contacts.add(contact);
    }
    return contacts;
  }
}
