package narif.poc.memoryleaks.controllers;

import narif.poc.memoryleaks.model.BadPerson;
import narif.poc.memoryleaks.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("persons")
public class PersonController {

    private Map<String, Map> cache = new HashMap<>();

    @GetMapping("improperEqualsAndHash")
    public void someBadMapEntries(){
        Map<BadPerson, String> leakingEntries= new HashMap<>();
        for (int i = 0; i < 1_000_000; i++) {
            leakingEntries.put(new BadPerson("FirstName","LastName"), i+"_person_added");
        }
        cache.put("LeakingFirstNameLastName", leakingEntries);
    }

    @GetMapping("properEqualsAndHash")
    public void someMapEntries(){
        Map<Person, String> nonLeakingEntries = new HashMap<>();
        for (int i = 0; i < 1_000_000; i++) {
            nonLeakingEntries.put(new Person("FirstName", "LastName"), i+"_person_added");
        }
        cache.put("NonLeakingFirstNameLastName", nonLeakingEntries);
    }

    @GetMapping("cache")
    public Map getCache(){
        return cache;
    }

}
