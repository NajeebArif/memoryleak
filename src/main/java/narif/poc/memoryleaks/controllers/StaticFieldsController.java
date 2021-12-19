package narif.poc.memoryleaks.controllers;

import narif.poc.memoryleaks.staticfields.AutoClosableResourceList;
import narif.poc.memoryleaks.staticfields.LeakingListClass;
import narif.poc.memoryleaks.staticfields.NonLeakingNormalClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("staticFields")
public class StaticFieldsController {

    @GetMapping("leakingListAllocation")
    public void populateList(){
        final var leakingListClass = new LeakingListClass();
        leakingListClass.populateList();
    }

    @GetMapping("nonLeakingCleanup")
    public void populateAutoClosableResource() throws Exception {
        try(final var autoClosableResourceList = new AutoClosableResourceList()){
            autoClosableResourceList.populateList();
        }
    }

    @GetMapping("nonLeakingLists")
    public void nonLeakingList(){
        final var nonLeakingNormalClass = new NonLeakingNormalClass();
        nonLeakingNormalClass.populateList();
    }
}
