///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  P1.main
// File:             Sym.java
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

/**
 * A container for symbol. The type of the symbol is stored.
 *
 * @author Reid Chen
 */
public class Sym {
    // type of this Sym
    private final String type;

    /**
     * Construct a sym
     * @param type type of this Sym
     */
    public Sym(String type) {
        this.type = type;
    }

    /**
     * Return this Sym's type.
     * @return this Sym's type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Return this Sym's type.
     * @return this Sym's type
     */
    @Override
    public String toString() {
        return this.type;
    }
}
