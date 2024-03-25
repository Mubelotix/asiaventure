package fr.insaRouen.iti.prog.asiaventure.elements.objets;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie.AllTestSerrurerie;

@RunWith(Suite.class)
@SuiteClasses({
    TestObjet.class,
    TestPiedDeBiche.class,
    AllTestSerrurerie.class,
})

public class AllTestObjets {}
