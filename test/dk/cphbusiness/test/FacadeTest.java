package dk.cphbusiness.test;

import dk.cphbusiness.entity.Address;
import dk.cphbusiness.entity.CityInfo;
import dk.cphbusiness.entity.Hobby;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.entity.Phone;
import dk.cphbusiness.exceptions.PersonNotFoundException;
import dk.cphbusiness.facade.PersonFacade;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FacadeTest {

    static EntityManagerFactory emf;
    static PersonFacade f;
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("CA2-Security-TestPU");
        f = new PersonFacade(emf);
    }

    @Test
    public void testGetPerson() throws IOException, PersonNotFoundException {  
        int id = 1;
        Person p = f.getPerson(id);
        
        Assert.assertEquals("Bo", p.getFirstName());
    }
    
    @Test
    public void testCreatePerson() throws IOException, PersonNotFoundException {
        Person p = new Person();
        p.setFirstName("TestCreate");
        p.setLastName("TestCreate");

        f.createPerson(p);
        Person p1 = f.getPerson(4);
        Assert.assertEquals("TestCreate", p1.getFirstName());
    }
    
    @Test
    public void testDeletePerson() throws IOException, PersonNotFoundException{
        f.removePerson(3);
        
        Person p = f.getPerson(3);
        Assert.assertEquals(null, p);
    }
    
    @Test
    public void testGetPersonByZip() throws IOException, PersonNotFoundException {
        int zip = 2970;
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT z FROM CityInfo z WHERE z.zip = :zip");
        query.setParameter("zip", zip);
        CityInfo ci;
        ci = (CityInfo) query.getSingleResult();

        Phone phone = new Phone();
        phone.setPhone("31344337");
        phone.setDescription("Hej med dig.");

        Person p3 = new Person();
        p3.setFirstName("fewew");
        p3.setLastName("dfvvveeve");
        Address a = new Address();
        a.setStreet("Gade 1");
        a.setCityInfo(ci);
        p3.setAddress(a);
        p3.addPhone(phone);
        
        f.createPerson(p3);
        
        List<Person> persons = f.getPersonsByZip(2970);
        
        Person p = persons.get(0);
        Assert.assertEquals(p.getFirstName(), p3.getFirstName());
    }
    
    @Test
    public void testGetPersonByPhone() throws IOException, PersonNotFoundException{
        Person p = new Person();
        p.setFirstName("Phone");
        p.setLastName("Phone");
        Phone ph = new Phone();
        ph.setPhone("31344337");
        ph.setDescription("jUnit Test");
        p.getPhones().add(ph);
        
        f.createPerson(p);
        
        Person p1 = f.getPersonByPhone("31344337");
        Assert.assertEquals(p.getFirstName(), p1.getFirstName());
    }
    
    @Test
    public void testGetPersonsWithHobby() throws IOException, PersonNotFoundException{
        Person p = new Person();
        p.setFirstName("Hobby");
        p.setLastName("Hobby");
        Hobby h = new Hobby();
        h.setHobbyName("Frimærkesamler.");
        h.setDescription("Samler hobbies Joe.");
        p.getHobbies().add(h);
        
        f.createPerson(p);
        
        List<Person> persons= f.getPersonsWithAHobby(h);
        Assert.assertEquals(p.getFirstName(), persons.get(0).getFirstName());
    }

}
