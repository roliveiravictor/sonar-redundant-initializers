package cleaner.enumerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victor.rocha
 */
public enum Patterns {
    LEFT_BRACKET("{"),
    BREAKPOINT(";"),
    DUMMY_INT(" = 0;"),
    DUMMY_BOOLEAN(" = false;"),
    DUMMY_OBJECT_INITIALIZER(" = null;");


    private String name;

    public String getName() {
        return name;
    }

    private Patterns(String name) {
        this.name = name;
    }
}
