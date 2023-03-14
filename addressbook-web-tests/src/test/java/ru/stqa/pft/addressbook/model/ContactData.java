package ru.stqa.pft.addressbook.model;


import java.util.Objects;

public class ContactData {

    private int id;
    private final String name;
    private final String lastName;
    private final String nickName;
    private final String telephone;
    private final String email;
    private String group;

 public int getId() {
       return id;
  }

    public ContactData(int id, String name, String lastName, String nickName, String telephone, String email, String group) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
        this.telephone = telephone;
        this.email = email;
        this.group = group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    public ContactData(String name, String lastName, String nickName, String telephone, String email, String group) {
        this.id = Integer.MAX_VALUE;
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