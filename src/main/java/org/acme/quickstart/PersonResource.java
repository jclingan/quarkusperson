package org.acme.quickstart;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/person")
public class PersonResource {

    @ConfigProperty(name = "message", defaultValue = "Howdy")
    String message;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Person addPerson(Person person) {
        person.persist();
        return person;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listall")
    public List<Person> listAll() {
        return Person.findAll().list();
    }
}