package fr.insaRouen.iti.prog.asiaventure.elements;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


import fr.insaRouen.iti.prog.asiaventure.elements.objets.AllTestObjets;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.AllTestStructure;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.AllTestVivants;

@RunWith(Suite.class)
@SuiteClasses({
    AllTestVivants.class,
    AllTestObjets.class,
    AllTestStructure.class,
    TestEntite.class,
})

public class AllTestElements {}
