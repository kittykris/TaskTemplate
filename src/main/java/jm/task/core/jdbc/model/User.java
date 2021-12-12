package jm.task.core.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class User {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null && this.getClass() != o.getClass()) return false;
        User user = (User) o;
        return (name == user.name && name.equals(user.getName()))
                && (lastName == user.lastName && lastName.equals(user.getLastName()))
                && (age == user.age);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + getName().hashCode();
        result = 37 * result + getName().hashCode();
        result = 37 * result + getAge();
        return result;
    }

    @Override
    public String toString() {
        return "User" +
                id +
                " (name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ')';
    }
}
