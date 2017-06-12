package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/people/", method = RequestMethod.GET)
    public Iterable<Person> getPeople(){
        return personRepository.findAll();
    }

//    @RequestMapping(value = "/people/", method = RequestMethod.GET)
//    public List<Person> getPeopleByLastName(@RequestParam("lastName") String lastName){
//        return personRepository.findByLastName(lastName);
//    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public Person getPeopleById(@PathVariable("id") Long id){
        return personRepository.findById(id);
    }

    @RequestMapping(value = "/people" ,method = RequestMethod.POST)
    public ResponseEntity<Void> createPerson(@RequestBody Person person, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("in create person " + person.toString());
        personRepository.save(person);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/people/{id}").buildAndExpand(person.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);


    }




}
