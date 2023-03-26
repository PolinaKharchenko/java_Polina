package ru.stqa.pft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator= new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }
    private void run() throws IOException {
        List<ContactData> contacts = ganerateContacts(count);
        save(contacts,new File(file));
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s\n",contact.getName(),contact.getLastName(), contact.getNickName(), contact.getAddress(),contact.getEmail()));
        }
        writer.close();
    }

    private static List<ContactData> ganerateContacts(int count) {
        List<ContactData> contacts  = new ArrayList<ContactData>();
        for (int i = 0; i< count; i++){
            contacts.add(new ContactData().withName(String.format("name %s", i))
                    .withLastName(String.format("lastname %s", i)). withNickName(String.format("nickname %s", i))
                    .withAddress(String.format("address %s", i)).withEmail(String.format("email %s",i)));
        }

        return contacts;
    }
}
