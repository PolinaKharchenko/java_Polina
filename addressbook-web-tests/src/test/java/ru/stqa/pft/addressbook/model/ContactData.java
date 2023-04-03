package ru.stqa.pft.addressbook.model;



import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;
@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")

public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Column(name = "firstname")
    private String name;
    @Column(name = "lastname")
    private String lastName;

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    private String nickName;
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String group;
    @Transient
    private String address;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private String email2;
    @Transient
    private String email3;
    @Transient
    private String email;
    @Column(name="photo")
    @Type(type = "text")
    private String photo;

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withAllEmails (String allEmails) {
        this.allEmails = allEmails;
        return this;
    }
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllPhones() {
        return allPhones;
    }
    public String getAddress() {
        return address;
    }


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

  //  public ContactData withTelephone(String telephone) {
    //    this.telephone = telephone;
      //  return this;
    //}

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }
    public String getWorkPhone() {
        return workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }
//  public String getTelephone() {
    //    return telephone;
    //}

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(nickName, that.nickName) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(group, that.group) && Objects.equals(address, that.address) && Objects.equals(allPhones, that.allPhones) && Objects.equals(allEmails, that.allEmails) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3) && Objects.equals(email, that.email) && Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, nickName, homePhone, mobilePhone, workPhone, group, address, allPhones, allEmails, email2, email3, email, photo);
    }
}