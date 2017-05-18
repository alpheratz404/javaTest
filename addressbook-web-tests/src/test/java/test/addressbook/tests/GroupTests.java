package test.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.addressbook.model.GroupData;
import test.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroups")
    public void groupTest(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        app.group().createG(group);
        assertThat(app.group().count(), equalTo(before.size() +1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void groupTest() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().createG(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();


        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyGroupListInUI();

    }
}


