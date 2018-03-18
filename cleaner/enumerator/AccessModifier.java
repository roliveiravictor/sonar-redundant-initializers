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
public enum AccessModifier {
    PRIVATE("private"),
    PROTECTED("protected"),
    PUBLIC("public"),
    FINAL("final");

    private String name;

    public String getName() {
        return name;
    }

    private AccessModifier(String name) {
        this.name = name;
    }
}
