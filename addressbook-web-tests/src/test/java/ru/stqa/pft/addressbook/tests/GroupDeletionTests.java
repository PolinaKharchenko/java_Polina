package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

   @Test
  public void testGroupDeletion()  {

       app.getNavigationHelper().goToGroupPage();
       GroupData group = new GroupData("test3", "test4", "test5");
       if(! app.getGroupHelper().isThereAGroup())
     {
             app.getGroupHelper().createGroup(group);
     }
       List<GroupData> before = app.getGroupHelper().getGroupList();
     app.getGroupHelper().selectGroup(before.size() - 1);
     app.getGroupHelper().deleteSelectedGroups();
     app.getGroupHelper().returnToGroupPage();
       List<GroupData> after = app.getGroupHelper().getGroupList();
       Assert.assertEquals(after.size(), before.size() - 1);

       before.remove(before.size() - 1);

      // Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      // before.sort(byId);
      // after.sort(byId);
       Assert.assertEquals(before, after);
       //Assert.assertEquals(before, after);
       //Assert.assertEquals(after.size(), before.size() + 1);
       // group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        }

}



