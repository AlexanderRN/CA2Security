package dk.cphbusiness.text;

import dk.cphbusiness.entity.Person;
import dk.cphbusiness.exceptions.PersonNotFoundException;
import dk.cphbusiness.facade.PersonFacade;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

}
