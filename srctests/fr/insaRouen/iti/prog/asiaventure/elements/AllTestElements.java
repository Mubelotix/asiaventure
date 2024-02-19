package srctests.fr.insaRouen.iti.prog.asiaventure.elements;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import srctests.fr.insaRouen.iti.prog.asiaventure.elements.objets.AllTestObjets;
import srctests.fr.insaRouen.iti.prog.asiaventure.elements.structure.AllTestStructure;

@RunWith(Suite.class)
@SuiteClasses({
    AllTestElements.class,
    AllTestObjets.class,
    AllTestStructure.class,
    TestEntite.class,
})

public class AllTestElements {}
