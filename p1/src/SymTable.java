///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  P1.main
// File:             SymTable.java
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
import java.util.*;

/**
 * Contains the symbol table, which is a list of hashmap.
 * Each hashmap corresponds to a scope.
 *
 * @author Reid Chen
 */
public class SymTable {
    // A list of hashmaps where each HashMap will store the identifiers declared
    // in one scope in the program being compiled.
    private final List<HashMap<String, Sym>> scopes;

    /**
     * This is the constructor; it should initialize the SymTable's List field
     * to contain a single, empty HashMap.
     */
    public SymTable() {
        this.scopes = new LinkedList<>();
        this.scopes.add(new HashMap<>());
    }

    /**
     * If this SymTable's list is empty, throw an EmptySymTableException.
     * If either name or sym (or both) is null, throw a NullPointerException.
     * If the first HashMap in the list already contains the given name as a
     * key, throw a DuplicateSymException.
     * Otherwise, add the given name and sym to the first HashMap in the list.
     * @param name name of the symbol, used as the key
     * @param sym symbol object, used as the value
     * @throws DuplicateSymException when name existed in the first hashmap
     * @throws EmptySymTableException when there is no sym table in the list
     */
    public void addDecl(String name, Sym sym) throws DuplicateSymException,
            EmptySymTableException {
        if (this.scopes.isEmpty()) {
            throw new EmptySymTableException();
        }
        if (name == null || sym == null) {
            throw new NullPointerException();
        }

        HashMap<String, Sym> firstHashMap = scopes.get(0);
        if (firstHashMap.containsKey(name)) {
            throw new DuplicateSymException();
        }
        firstHashMap.put(name, sym);
    }

    /**
     * Add a new, empty HashMap to the front of the list.
     */
    public void addScope() {
        this.scopes.add(0, new HashMap<>());
    }

    /**
     * If this SymTable's list is empty, throw an EmptySymTableException.
     * Otherwise, if the first HashMap in the list contains name as a key,
     * return the associated Sym; otherwise, return null.
     * @param name name of the symbol
     * @return corresponding Sym object:
     */
    public Sym lookupLocal(String name) throws EmptySymTableException {
        if (this.scopes.isEmpty()) {
            throw new EmptySymTableException();
        }
        final HashMap<String, Sym> firstHashMap= scopes.get(0);
        return firstHashMap.get(name);
    }

    /**
     * If this SymTable's list is empty, throw an EmptySymTableException.
     * If any HashMap in the list contains name as a key,
     * return the first associated Sym (i.e., the one from the HashMap that is
     * closest to the front of the list); otherwise, return null.
     * @throws EmptySymTableException if scopes list is empty
     * @param name name of the symbol
     * @return the first associated Sym
     */
    public Sym lookupGlobal(String name) throws EmptySymTableException {
        if (this.scopes.isEmpty()) {
            throw new EmptySymTableException();
        }
        Sym associatedSym = null;
        for (HashMap<String, Sym> map: this.scopes) {
            associatedSym = map.get(name);
            if (associatedSym != null) {
                break;
            }
        }
        return associatedSym;
    }

    /**
     * If this SymTable's list is empty, throw an EmptySymTableException;
     * otherwise, remove the HashMap from the front of the list.
     * To clarify, throw an exception only if before attempting to remove,
     * the list is empty (i.e. there are no HashMaps to remove).
     * @throws EmptySymTableException if scopes list is empty
     */
    public void removeScope() throws EmptySymTableException {
        if (this.scopes.isEmpty()) {
            throw new EmptySymTableException();
        }
        this.scopes.remove(0);
    }

    /**
     * This method is for debugging.
     * First, print “\nSym Table\n”.
     * Then, for each HashMap M in the list, print M.toString()
     * followed by a newline.
     * Finally, print one more newline. All output should go to System.out.
     */
    public void print() {
        System.out.print("\nSym Table\n");
        this.scopes.forEach(hashmap -> System.out.println(hashmap.toString()));
        System.out.println();
    }

}
