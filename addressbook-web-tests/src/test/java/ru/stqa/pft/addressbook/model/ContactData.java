package ru.stqa.pft.addressbook.model;


public class ContactData {

    private final String name;
    private final String lastName;
    private final String nickName;
    private final String telephone;
    private final String email;
    private String group;

    public ContactData(String name, String lastName, String nickName, String telephone, String email, String group) {
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
        this.telephone = telephone;
        this.email = email;
        this.group = group;
    }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getNickName() { return nickName; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public String getGroup() { return group; }
}