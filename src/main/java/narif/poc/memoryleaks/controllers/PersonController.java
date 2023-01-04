package narif.poc.memoryleaks.controllers;

import narif.poc.memoryleaks.model.BadPerson;
import narif.poc.memoryleaks.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

@RestController
@RequestMapping("persons")
public class PersonController {

    private Map<Person, String> personStringMap = new HashMap<>();
    private Map<BadPerson, String> badPersonStringWeakHashMap = new WeakHashMap<>();
    private Map<BadPerson, String> badPersonStringMap = new HashMap<>();

    @GetMapping("improperEqualsAndHash")
    public void someBadMapEntries(){
        for (int i = 0; i < 1_000_000; i++) {
            badPersonStringMap.put(new BadPerson("FirstName","LastName"), i+"_person_added");
        }
    }

    @GetMapping("improperEqualsAndHashV2")
    public void someBadMapEntriesWithWeakHashMap(){
        for (int i = 0; i < 1_000_000; i++) {
            badPersonStringWeakHashMap.put(new BadPerson("FirstName","LastName"), i+"_person_added");
        }
    }

    @GetMapping("properEqualsAndHash")
    public void someMapEntries(){
        for (int i = 0; i < 1_000_000; i++) {
            personStringMap.put(new Person("FirstName", "LastName"), i+"_person_added");
        }
    }

    @GetMapping("personCache")
    public Map<Person, String> getCache(){
        return personStringMap;
    }

    @GetMapping("personLeakingWeakCache")
    public Map<BadPerson, String> fetchCache(){
        return badPersonStringWeakHashMap;
    }

    @GetMapping("personLeakingCache")
    public Map<BadPerson, String> fetchLeakingCache(){
        return badPersonStringMap;
    }

}
