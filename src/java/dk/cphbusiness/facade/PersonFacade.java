package dk.cphbusiness.facade;

import dk.cphbusiness.entity.CityInfo;
import dk.cphbusiness.entity.Hobby;
import dk.cphbusiness.entity.InfoEntity;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.entity.Phone;
import dk.cphbusiness.exceptions.PersonNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class PersonFacade {

    static EntityManagerFactory emf;

    public PersonFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public  Person getPerson(int id) throws PersonNotFoundException{
        EntityManager em = getEntityManager();
        Long idL = (long) id;
        Person p = null;
        try {
        p = em.find(Person.class, idL);
        } catch (Error e) {
            throw new PersonNotFoundException("Person not found.");
        }

        return p;
    }

    public List<Person> getPersons() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM Person p");
        return query.getResultList();
    }

    public  void createPerson(Person p) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    public  void removePerson(int id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        Long idL = (long) id;
        Person p = null;

        try {
            p = em.find(Person.class, idL);
        } catch (Error e) {
            throw new PersonNotFoundException("Person not found.");
        }

        try {
            if (p != null) {
                em.getTransaction().begin();
                em.remove(p);
                em.getTransaction().commit();
                em.close();
            }
        } catch (Error e) {
            throw new PersonNotFoundException("Person not found.");
        }
    }

    public  List<Person> getPersonsByZip(int zip) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        Query query = null;
        try {
        query = em.createQuery("SELECT p FROM InfoEntity p WHERE p.address.cityInfo.zip = :zip");
        query.setParameter("zip", zip);
        } catch (Error e) { 
             throw new PersonNotFoundException("Person not found.");   
        }
        return query.getResultList();
    }

    public  List<CityInfo> getZipcodes() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT z.zip FROM CityInfo z");
        return query.getResultList();
    }

    public  Person getPersonByPhone(String phoneNr) throws PersonNotFoundException{
        Person person = new Person();
        EntityManager em = getEntityManager();
        Long phoneId = 0L;
        try {
        for (int i = 0; i < person.getPhones().size(); i++) {
            if (person.getPhones().get(i).getPhone().equals(phoneNr)) {
                phoneId = person.getPhones().get(i).getId();
            }
        }
        Phone person1 = em.find(Phone.class, phoneId);
        Query query = em.createQuery("SELECT p FROM Person p WHERE :phone IN (p.phones)");
        query.setParameter("phone", person1);

        person = (Person) query.getSingleResult();
        } catch (Error e) {
            throw new PersonNotFoundException();
        }
        return person;
    }

    public  List<Person> getPersonsWithAHobby(Hobby hobby) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        int hobbyId = hobby.getId().intValue();
        Query query = null;
        try {
        query = em.createQuery("SELECT p FROM Person p WHERE p.hobbies.id :hobby");
        query.setParameter("hobby", hobbyId);
        } catch (Error e) {
            throw new PersonNotFoundException();
        }

        return query.getResultList();
    }

    public  int getPersonCountWithHobby(Hobby hobby) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        int hobbyId = hobby.getId().intValue();
        Query query = null;
        try {
        query = em.createQuery("SELECT p FROM Person p WHERE p.hobbies.id :hobby");
        query.setParameter("hobby", hobbyId);
        } catch (Error e) {
              throw new PersonNotFoundException("Person not found.");  
        }

        return query.getResultList().size() + 1;
    }

}
