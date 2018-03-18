package cleaner.utils;

import cleaner.enumerator.Patterns;
import cleaner.enumerator.AccessModifier;
import cleaner.enumerator.PrimitiveType;

/**
 *
 * @author victor.rocha
 */
public class LineUtils {

    public static boolean isPrimitiveTarget(String line) {
        return  containsInitializer(line) &&
                isNotMethodImplementation(line) &&
                isDummyInitializer(line);
    }

    private static boolean containsInitializer(String line) {
        return  line.contains(AccessModifier.PRIVATE.getName()) ||
                line.contains(AccessModifier.PUBLIC.getName()) || 
                line.contains(AccessModifier.PROTECTED.getName());
        }

    private static boolean isNotMethodImplementation(String line) {
       return !line.contains(Patterns.LEFT_BRACKET.getName());
    }

    private static boolean isDummyInitializer(String line) {
        return   isIntWithDummy(line) ||
                 isBoolWithDummy(line) ||
                 isCustomObjectWithDummy(line) ;
    }   

    public static String clean(String line) {
        line = line.replace(Patterns.DUMMY_OBJECT_INITIALIZER.getName(), Patterns.BREAKPOINT.getName());
        line = line.replace(Patterns.DUMMY_INT.getName(), Patterns.BREAKPOINT.getName());
        line = line.replace(Patterns.DUMMY_BOOLEAN.getName(), Patterns.BREAKPOINT.getName());
        
        return line;
    }

    private static boolean isIntWithDummy(String line) {
        return line.contains(PrimitiveType.INT.getName()) && line.contains(Patterns.DUMMY_INT.getName()) && !line.contains(AccessModifier.FINAL.getName());
    }

    private static boolean isBoolWithDummy(String line) {
        return line.contains(PrimitiveType.BOOLEAN.getName()) && line.contains(Patterns.DUMMY_BOOLEAN.getName()) && !line.contains(AccessModifier.FINAL.getName());
    }

    private static boolean isCustomObjectWithDummy(String line) {
        return  !line.contains(PrimitiveType.BYTE.getName()) &&
                !line.contains(PrimitiveType.SHORT.getName()) &&
                !line.contains(PrimitiveType.INT.getName()) &&
                !line.contains(PrimitiveType.LONG.getName()) &&
                !line.contains(PrimitiveType.FLOAT.getName()) &&
                !line.contains(PrimitiveType.DOUBLE.getName()) &&
                !line.contains(PrimitiveType.CHAR.getName()) &&
                !line.contains(PrimitiveType.STRING.getName()) &&
                !line.contains(PrimitiveType.BOOLEAN.getName()) &&
                line.contains(Patterns.DUMMY_OBJECT_INITIALIZER.getName());
    }
}
