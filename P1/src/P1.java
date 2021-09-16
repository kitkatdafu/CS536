///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  P1.main
// File:             P1.java
// Semester:         CS 536 Fall 2021
//
// Author:           Reid Chen
// CS Login:         reid
// Lecturer's Name:  Aws Albarghouthi
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          (x)
//
// Online sources:   (x)
//////////////////////////// 80 columns wide //////////////////////////////////
import java.lang.reflect.InvocationTargetException;
import java.io.*;

import java.lang.reflect.Method;

/**
 * Test class for this assignment.
 *
 * @author Reid Chen
 */
public class P1 {
    /**
     * Main method, entry of the test
     * @param args command lind arguments, not used
     * @throws InvocationTargetException throws by invoke
     * @throws IllegalAccessException throws by invoke
     */
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        // Get all methods declared in this class
        Method[] methods = P1.class.getDeclaredMethods();
        boolean allSuccess = true;
        for (Method method: methods) {
            // if this method is main or a lambda, skip
            if (method.getName().equals("main") || method.getName().contains(
                    "lambda")) {
                continue;
            }
            // call the method
            final boolean hasPassed = (boolean) method.invoke(null);
            if (!hasPassed) {
                System.err.println("NOT PASSED:");
                System.err.println("\t" + method);
                allSuccess = false;
            }
        }
        if (allSuccess) {
            System.out.println("PASSED!");
        }
    }

    /**
     * Test if Sym initialized with correct type
     * @return true if test passed
     */
    public static boolean testSymInitializedWithCorrectType() {
        final String type = "ser";
        Sym sym = new Sym(type);
        return sym.getType().equals(type);
    }

    /**
     * Test if Sym's string representation is correct
     * @return true if test passed
     */
    public static boolean testSymProductCorrectString() {
        final String type = "haber";
        Sym sym = new Sym(type);
        return sym.toString().equals(type);
    }

    /**
     * Test if SymTable is initialized with a list that contains an empty
     * HashMap
     * @return true if test passed
     */
    public static boolean testSymTableInitializedAListWithAHashMap() {
        SymTable table = new SymTable();
        try {
            table.removeScope();
        } catch (EmptySymTableException e) {
            return false;
        }
        return true;
    }

    /**
     * Test if SymTable is initialized with a list that contains an empty
     * HashMap
     * @return true if test passed
     */
    public static boolean testSymTableInitializedAListWithAnEmptyHashMap() {
        SymTable table = new SymTable();
        try {
            final Sym result = table.lookupLocal("");
            return result == null;
        } catch (EmptySymTableException e) {
            return false;
        }
    }

    /**
     * Test if SymTable can remove HashMap successfully
     * @return true if test passed
     */
    public static boolean testSymTableRemoveHashMap() {
        SymTable table = new SymTable();
        try {
            table.removeScope();
        } catch (Exception e) {
            return false;
        }
        try {
            table.removeScope();
        } catch (EmptySymTableException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Test if addDecl() throws null pointer exception when one of the
     * parameters is null
     * @return true if test passed
     */
    public static boolean testSymTableAddDeclThrowsNPE1() {
        SymTable table = new SymTable();
        try {
            table.addDecl(null, new Sym("Hello"));
        } catch (NullPointerException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Test if addDecl() throws null pointer exception when one of the
     * parameters is null
     * @return true if test passed
     */
    public static boolean testSymTableAddDeclThrowsNPE2() {
        SymTable table = new SymTable();
        try {
            table.addDecl("Hello", null);
        } catch (NullPointerException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Test if addDecl() throws null pointer exception when one of the
     * parameters is null
     * @return true if test passed
     */
    public static boolean testSymTableAddDeclThrowsNPE3() {
        SymTable table = new SymTable();
        try {
            table.addDecl(null, null);
        } catch (NullPointerException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Test if addDecl() throws EmptySymTableException when there is not
     * HashMaps in the list
     * @return true if test passed
     */
    public static boolean testSymTableThrowsEmptySymTableException() {
        SymTable table = new SymTable();
        try {
            table.removeScope();
        } catch (Exception e) {
            return false;
        }
        try {
            table.addDecl("Hello!", new Sym("World"));
        } catch (EmptySymTableException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Test if addDecl can successfully add Sym
     * @return true if test passed
     */
    public static boolean testSymTableAddDecl1() {
        SymTable table = new SymTable();
        try {
            final Sym sym = new Sym("World!");
            final String name = "Hello!";
            table.addDecl(name, sym);
            final Sym result = table.lookupLocal(name);
            return sym == result;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Test if addDecl can successfully add Sym
     * @return true if test passed
     */
    public static boolean testSymTableAddDecl2() {
        SymTable table = new SymTable();
        try {
            final Sym sym1 = new Sym("World!");
            final String name1 = "Hello!";
            table.addDecl(name1, sym1);
            final Sym result1 = table.lookupLocal(name1);

            final Sym sym2 = new Sym("World!");
            final String name2 = "Hel";
            table.addDecl(name2, sym2);
            final Sym result2 = table.lookupLocal(name2);

            return sym1 == result1 && sym2 == result2;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Test if DuplicateSymException throws when duplicated Sym key is trying
     * to be added
     * @return true if test passed
     */
    public static boolean testSymTableThrowsDuplicateSymException1() {
        SymTable table = new SymTable();
        try {
            final Sym sym = new Sym("World!");
            final String name = "Hello!";
            table.addDecl(name, sym);
            table.addDecl(name, sym);
            return false;
        } catch (DuplicateSymException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Test if DuplicateSymException throws when duplicated Sym key is trying
     * to be added
     * @return true if test passed
     */
    public static boolean testSymTableThrowsDuplicateSymException2() {
        SymTable table = new SymTable();
        try {
            final Sym sym = new Sym("World!");
            final String name1 = "Hello!";
            final String name2 = "Hello!";
            table.addDecl(name1, sym);
            table.addDecl(name2, sym);
            return false;
        } catch (DuplicateSymException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Test if DuplicateSymException throws when duplicated Sym key is trying
     * to be added
     * @return true if test passed
     */
    public static boolean testSymTableThrowsDuplicateSymException3() {
        SymTable table = new SymTable();
        try {
            final Sym sym1 = new Sym("World!");
            final Sym sym2 = new Sym("World!");
            final String name = "Hello!";
            table.addDecl(name, sym1);
            table.addDecl(name, sym2);
            return false;
        } catch (DuplicateSymException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Test if addDecl() add symbol to the hashmap at index 0 in the list
     * @return true if test passed
     */
    public static boolean testSymTableAddDeclOnlyAddToFirstHashMap() {
        SymTable table = new SymTable();
        try {
            table.addScope();

            final Sym sym = new Sym("World!");
            final String name = "Hello!";
            table.addDecl(name, sym);

            table.removeScope();

            return table.lookupLocal(name) == null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Test if addScope() add scope to the beginning of the list
     * @return true if test passed
     */
    public static boolean testSymTableAddScopeAddToFront() {
        SymTable table = new SymTable();
        try {
            final String name = "name";
            final Sym sym = new Sym("sym");
            table.addDecl(name, sym);
            table.addScope();
            return table.lookupLocal(name) == null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Test if lookupLocal() throws EmptySymTableException when there is not
     * HashMap in the list
     * @return true if test passed
     */
    public static boolean testSymTableLookUpLocalThrowsEmptySymTableExcep1() {
        SymTable table = new SymTable();
        try {
            table.removeScope();
            final String name = "name";
            table.lookupLocal(name);
            return false;
        } catch (EmptySymTableException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Test if lookupLocal() throws EmptySymTableException when there is not
     * HashMap in the list
     * @return true if test passed
     */
    public static boolean testSymTableLookUpLocalThrowsEmptySymTableExcep2() {
        SymTable table = new SymTable();
        try {
            final String name = "name";
            final Sym sym = new Sym("sym");
            table.addDecl(name, sym);
            table.removeScope();
            table.lookupLocal(name);
            return false;
        } catch (EmptySymTableException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Test if lookupGlobal() throws EmptySymTableException when there is not
     * HashMap in the list
     * @return true if test passed
     */
    public static boolean testSymTableLookUpGlobalThrowsEmptySymTableExcep1() {
        SymTable table = new SymTable();
        try {
            table.removeScope();
            final String name = "name";
            table.lookupGlobal(name);
            return false;
        } catch (EmptySymTableException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Test if lookupGlobal() throws EmptySymTableException when there is not
     * HashMap in the list
     * @return true if test passed
     */
    public static boolean testSymTableLookUpGlobalThrowsEmptySymTableExcep2() {
        SymTable table = new SymTable();
        try {
            final String name = "name";
            final Sym sym = new Sym("sym");
            table.addDecl(name, sym);
            table.removeScope();
            table.lookupGlobal(name);
            return false;
        } catch (EmptySymTableException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if lookupGlobal returns the match that resides in the map that
     * has the lowest index
     * @return true if test passed
     */
    public static boolean testSymTableLookupGlobalFindClosestToFront1() {
        SymTable table = new SymTable();
        try {
            table.addScope();
            table.addDecl("123", new Sym("1231233"));

            table.addScope();
            final String name = "name";
            final Sym sym1 = new Sym("sym");
            table.addDecl(name, sym1);

            table.addScope();
            final Sym sym2 = new Sym("sym");
            table.addDecl(name, sym2);

            return sym2 == table.lookupGlobal(name);
        } catch (EmptySymTableException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if lookupGlobal returns the match that resides in the map that
     * has the lowest index
     * @return true if test passed
     */
    public static boolean testSymTableLookupGlobalFindClosestToFront2() {
        SymTable table = new SymTable();
        try {
            table.addScope();
            table.addDecl("123", new Sym("1231233"));

            table.addScope();
            final String name = "name";
            final Sym sym1 = new Sym("sym");
            table.addDecl(name, sym1);

            table.addScope();
            final Sym sym2 = new Sym("sym");
            table.addDecl(name, sym2);

            table.removeScope();

            return sym1 == table.lookupGlobal(name);
        } catch (EmptySymTableException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if sym key cannot be found when scope that contains it is removed
     * @return true if test passed
     */
    public static boolean testSymTableRemoveScopeRemovesAndCannotFind() {
        SymTable table = new SymTable();
        try {
            final String name = "name";
            final Sym sym1 = new Sym("sym");
            table.addDecl(name, sym1);

            table.removeScope();

            return null == table.lookupGlobal(name);
        } catch (EmptySymTableException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if print() begins with a new line character
     * @return true if test passed
     */
    public static boolean testSymTablePrintBeginWithNewLine() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SymTable table = new SymTable();
        table.print();

        System.setOut(stdout);
        return out.toString().toCharArray()[0] == '\n';
    }

    /**
     * Check if print() starts with the correct string
     * @return true if test passed
     */
    public static boolean testSymTablePrintSymTable() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SymTable table = new SymTable();
        table.print();

        System.setOut(stdout);
        return out.toString().startsWith("\nSym Table\n");
    }

    /**
     * Check if print() contains prints no HashMap when SymTable list is empty
     * @return true if test passed
     */
    public static boolean testSymTableEmptyTablePrintNothingMore() {
        // Redirect stdout so that they can be checked
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SymTable table = new SymTable();
        try {
            table.removeScope();
        } catch (Exception e) {
            return false;
        }
        table.print();

        System.setOut(stdout);
        return out.toString().equals("\nSym Table\n\n");
    }

    /**
     * Check if print() contains the correct number of new lines
     * @return True if test passed
     */
    public static boolean testSymTableCorrectNumberOfNewLine() {
        // Redirect stdout so that they can be checked
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SymTable table = new SymTable();

        table.addScope();
        table.addScope();
        table.addScope();

        table.print();

        System.setOut(stdout);
        return out.toString().chars().filter(x -> x == '\n').count() == 7;
    }
}

