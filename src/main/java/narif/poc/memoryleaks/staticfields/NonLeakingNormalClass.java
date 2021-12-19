package narif.poc.memoryleaks.staticfields;

import narif.poc.memoryleaks.model.Person;

import java.util.ArrayList;
import java.util.List;

public class NonLeakingNormalClass {

    private List<Person> leakingPersons = new ArrayList<>();

    public void populateList(){
        for (int i = 0; i < 1_000_000; i++) {
            leakingPersons.add(new Person(i+"_FirstName", i+"_LastName"));
        }
    }
}
