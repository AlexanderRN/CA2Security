package dk.cphbusiness.facade;

import dk.cphbusiness.entity.Company;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.exceptions.CompanyNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class CompanyFacade {
    
    EntityManagerFactory emf;

    public CompanyFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Company getCompanyByCvr(int cvr) throws CompanyNotFoundException{
        EntityManager em = getEntityManager();
        Company c = null;
        try {
            c = em.find(Company.class, cvr);
        } catch (Exception e) {
            throw new CompanyNotFoundException("Company not found");
        }

        return c;
    }
    
    public Company getCompanyByPhone(String phone) throws CompanyNotFoundException {
        EntityManager em = getEntityManager();

        Company c = null;
        try {
            c = em.find(Company.class, phone);
        } catch (Exception e) {
            throw new CompanyNotFoundException("Company not found");
        }

        return c;
    }
    
    public List<Company> getCompaniesWithMoreThanEmployees(int employees) {
        EntityManager em = getEntityManager();

        Query query = em.createQuery("SELECT c FROM Company c WHERE c.numEmployees > :emp");
        query.setParameter("emp", employees);

        return query.getResultList();
    }
    
    public void createCompany(Company c) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }
    
}
