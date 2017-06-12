package hello;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Subsegment;
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

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public Iterable<Person> getPeople(){
        Subsegment subsegment = AWSXRay.beginSubsegment("getPeople");
        XRayUtils.addMetadata();
        try {
            return personRepository.findAll();
        } finally {
            AWSXRay.endSubsegment();
        }
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> getPeopleByLastName(@RequestParam("lastName") String lastName){
        Subsegment subsegment = AWSXRay.beginSubsegment("getPeopleByLastName");
        XRayUtils.addMetadata();
        try {
            return personRepository.findByLastName(lastName);
        } finally {
            AWSXRay.endSubsegment();
        }
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public Person getPeopleById(@PathVariable("id") Long id){
        Subsegment subsegment = AWSXRay.beginSubsegment("getPeopleByID");
        XRayUtils.addMetadata();
        try {
            return personRepository.findById(id);
        } finally {
            AWSXRay.endSubsegment();
        }
    }

    @RequestMapping(value = "/people" ,method = RequestMethod.POST)
    public ResponseEntity<Void> createPerson(@RequestBody Person person, UriComponentsBuilder uriComponentsBuilder) {
        Subsegment subsegment = AWSXRay.beginSubsegment("createPerson");
        XRayUtils.addMetadata();
        try {
            personRepository.save(person);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponentsBuilder.path("/people/{id}").buildAndExpand(person.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } finally {
            AWSXRay.endSubsegment();
        }


    }




}
