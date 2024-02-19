package srctests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    AllTestElements.class,
    AllTestObjets.class,
    AllTestStructure.class,
    TestEntite.class,
})

public class AllTestElements {}
