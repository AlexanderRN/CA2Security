package dk.cphbusiness.tester;

import com.google.gson.Gson;
import dk.cphbusiness.entity.Address;
import dk.cphbusiness.entity.CityInfo;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.entity.Phone;
import dk.cphbusiness.exceptions.PersonNotFoundException;
import dk.cphbusiness.facade.PersonFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Tester {

    static EntityManagerFactory emf;

    public static void main(String[] args) throws PersonNotFoundException {
        emf = Persistence.createEntityManagerFactory("CA2-Security-TestPU");
        PersonFacade f = new PersonFacade(emf);

        EntityManager em = emf.createEntityManager();
        int zip = 2970;
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

        Person p = new Person();
        p.setFirstName("Alexander");
        p.setLastName("Nielsen");

        Person p2 = new Person();
        p2.setFirstName("Test");
        p2.setLastName("Testensen");

        f.createPerson(p);
        f.createPerson(p2);
        f.createPerson(p3);

        System.out.println(f.getPersons());
        f.removePerson(1);

        String json = new Gson().toJson(f.getPersons());

        System.out.println("Person 1: " + json);

        String json1 = new Gson().toJson(f.getZipcodes());

        System.out.println("All zips: " + json1);

        String json2 = new Gson().toJson(f.getPersonByPhone("31344337"));
        System.out.println("Person @31344337" + json2);
        String json3 = new Gson().toJson(f.getPersonsByZip(2970));

        System.out.println("Person by zip: " + json3);

    }
}
