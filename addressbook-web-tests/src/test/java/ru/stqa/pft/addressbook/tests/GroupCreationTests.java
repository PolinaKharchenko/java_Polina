package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {



    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        //List<Object[]> list = new ArrayList<>();
       try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
        String xml = "";
        String line = reader.readLine();
        while(line != null){
            xml += line;
           // String[] split = line.split(";");
          //  list.add(new Object[]{new GroupData().withName(split[0]).withFooter(split[1]).withHeader(split[2])});
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        xstream.allowTypes(new Class[]{GroupData.class});
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();}
        //return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        //List<Object[]> list = new ArrayList<>();
       try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))){
        String json = "";
        String line = reader.readLine();
        while(line != null){
            json += line;
            // String[] split = line.split(";");
            //  list.add(new Object[]{new GroupData().withName(split[0]).withFooter(split[1]).withHeader(split[2])});
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups =  gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();}

    }
    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {

        app.goTo().groupPage();
        Groups before = app.db().groups();
        app.goTo().groupPage();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        app.goTo().groupPage();
        Groups after = app.db().groups();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyGroupListInUI();
    }

    @Test
    public void testBadGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData group = new GroupData().withName("test2'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before));
    }
}
