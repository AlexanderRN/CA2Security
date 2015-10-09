package dk.cphbusiness.tester;

import javax.persistence.Persistence;


public class SchemaBuilder {
    public static void main(String[] args) {
        Persistence.generateSchema("CA2-Security-TestPU", null);
    }
}
