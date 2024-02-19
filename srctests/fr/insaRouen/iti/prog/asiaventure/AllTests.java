package fr.insaRouen.iti.prog.asiaventure;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insaRouen.iti.prog.asiaventure.elements.AllTestElements;

@RunWith(Suite.class)
@SuiteClasses({
    AllTestElements.class,
    TestMonde.class,
})

public class AllTests {}
