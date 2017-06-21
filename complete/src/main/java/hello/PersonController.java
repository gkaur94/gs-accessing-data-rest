package hello;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Subsegment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private  FriendsRepository friendsRepository;

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public List<Person> getPeople(){
        Subsegment subsegment = AWSXRay.beginSubsegment("getPeople");
        XRayUtils.addMetadata();
        try {
            return personRepository.findAllByOrderByLastNameAsc();
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


    @RequestMapping(value = "/friendship", method = RequestMethod.POST)
    public ResponseEntity<Void> createFriendship(@RequestBody Friendship friendship, UriComponentsBuilder uriComponentsBuilder) {
        Subsegment subsegment = AWSXRay.beginSubsegment("createPerson");
        XRayUtils.addMetadata();
        try {
            friendsRepository.save(friendship);
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } finally {
            AWSXRay.endSubsegment();
        }


    }






}
