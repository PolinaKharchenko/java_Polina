package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

  @Test

  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();

    if(! app.getGroupHelper().isThereAGroup())
    {
      app.getGroupHelper().createGroup(new GroupData("test3", "test4", "test5"));
    }

    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test3", "test2", "test1"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}
