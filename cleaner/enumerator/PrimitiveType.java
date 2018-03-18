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
public enum PrimitiveType {
    BYTE("byte"),
    SHORT("short"),
    INT("int"),
    LONG("long"),
    FLOAT("float"),
    DOUBLE("double"),
    CHAR("char"),
    STRING("String"),
    BOOLEAN("boolean");
    
    private String name;

    public String getName() {
        return name;
    }

    private PrimitiveType(String name) {
        this.name = name;
    }
}
