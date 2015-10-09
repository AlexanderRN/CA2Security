/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author AlexanderNielsen
 */
@Entity
public class Person extends InfoEntity  {
    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy = "persons")
    private List<Hobby> hobbies;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
