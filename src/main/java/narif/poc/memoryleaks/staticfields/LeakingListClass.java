package narif.poc.memoryleaks.staticfields;

import narif.poc.memoryleaks.model.Person;

import java.util.ArrayList;
import java.util.List;

public class LeakingListClass {

    private static List<Person> leakingPersons = new ArrayList<>();

    public void populateList(){
        for (int i = 0; i < 500_000; i++) {
            leakingPersons.add(new Person(i+"_FirstName", i+"_LastName"));
        }
    }
}
