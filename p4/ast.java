import java.io.*;
import java.util.*;

// **********************************************************************
// The ASTnode class defines the nodes of the abstract-syntax tree that
// represents a b program.
//
// Internal nodes of the tree contain pointers to children, organized
// either in a list (for nodes that may have a variable number of 
// children) or as a fixed set of fields.
//
// The nodes for literals and ids contain line and character number
// information; for string literals and identifiers, they also contain a
// string; for integer literals, they also contain an integer value.
//
// Here are all the different kinds of AST nodes and what kinds of children
// they have.  All of these kinds of AST nodes are subclasses of "ASTnode".
// Indentation indicates further subclassing:
//
//     Subclass            Kids
//     --------            ----
//     ProgramNode         DeclListNode
//     DeclListNode        linked list of DeclNode
//     DeclNode:
//       VarDeclNode       TypeNode, IdNode, int
//       FnDeclNode        TypeNode, IdNode, FormalsListNode, FnBodyNode
//       FormalDeclNode    TypeNode, IdNode
//       StructDeclNode    IdNode, DeclListNode
//
//     FormalsListNode     linked list of FormalDeclNode
//     FnBodyNode          DeclListNode, StmtListNode
//     StmtListNode        linked list of StmtNode
//     ExpListNode         linked list of ExpNode
//
//     TypeNode:
//       IntNode           -- none --
//       BoolNode          -- none --
//       VoidNode          -- none --
//       StructNode        IdNode
//
//     StmtNode:
//       AssignStmtNode      AssignNode
//       PreIncStmtNode     ExpNode
//       PreDecStmtNode     ExpNode
//       ReceiveStmtNode        ExpNode
//       PrintStmtNode       ExpNode
//       IfStmtNode          ExpNode, DeclListNode, StmtListNode
//       IfElseStmtNode      ExpNode, DeclListNode, StmtListNode,
//                                    DeclListNode, StmtListNode
//       WhileStmtNode       ExpNode, DeclListNode, StmtListNode
//       RepeatStmtNode      ExpNode, DeclListNode, StmtListNode
//       CallStmtNode        CallExpNode
//       ReturnStmtNode      ExpNode
//
//     ExpNode:
//       IntLitNode          -- none --
//       StrLitNode          -- none --
//       TrueNode            -- none --
//       FalseNode           -- none --
//       IdNode              -- none --
//       DotAccessNode       ExpNode, IdNode
//       AssignNode          ExpNode, ExpNode
//       CallExpNode         IdNode, ExpListNode
//       UnaryExpNode        ExpNode
//         UnaryMinusNode
//         NotNode
//       BinaryExpNode       ExpNode ExpNode
//         PlusNode     
//         MinusNode
//         TimesNode
//         DivideNode
//         AndNode
//         OrNode
//         EqualsNode
//         NotEqualsNode
//         LessNode
//         GreaterNode
//         LessEqNode
//         GreaterEqNode
//
// Here are the different kinds of AST nodes again, organized according to
// whether they are leaves, internal nodes with linked lists of kids, or
// internal nodes with a fixed number of kids:
//
// (1) Leaf nodes:
//        IntNode,   BoolNode,  VoidNode,  IntLitNode,  StrLitNode,
//        TrueNode,  FalseNode, IdNode
//
// (2) Internal nodes with (possibly empty) linked lists of children:
//        DeclListNode, FormalsListNode, StmtListNode, ExpListNode
//
// (3) Internal nodes with fixed numbers of kids:
//        ProgramNode,     VarDeclNode,     FnDeclNode,     FormalDeclNode,
//        StructDeclNode,  FnBodyNode,      StructNode,     AssignStmtNode,
//        PreIncStmtNode, PreDecStmtNode, ReceiveStmtNode,   PrintStmtNode   
//        IfStmtNode,      IfElseStmtNode,  WhileStmtNode,  RepeatStmtNode,
//        CallStmtNode
//        ReturnStmtNode,  DotAccessNode,   AssignExpNode,  CallExpNode,
//        UnaryExpNode,    BinaryExpNode,   UnaryMinusNode, NotNode,
//        PlusNode,        MinusNode,       TimesNode,      DivideNode,
//        AndNode,         OrNode,          EqualsNode,     NotEqualsNode,
//        LessNode,        GreaterNode,     LessEqNode,     GreaterEqNode
//
// **********************************************************************

// **********************************************************************
// <<<ASTnode class (base class for all other kinds of nodes)>>>
// **********************************************************************

abstract class ASTnode {
    // every subclass must provide an unparse operation
    public abstract void unparse(PrintWriter p, int indent);

    // this method can be used by the unparse methods to do indenting
    protected void addIndent(PrintWriter p, int indent) {
        for (int k = 0; k < indent; k++)
            p.print(" ");
    }

    /**
     * More than one declaration of an identiﬁer in a given scope (note: includes
     * identiﬁer associated with a struct deﬁnition)
     */
    public static void multiplyDeclaredIdentifier(int lineNum, int charNum) {
        ErrMsg.fatal(lineNum, charNum, "Multiply declared identifier");
    }

    /**
     * Use of an undeclared identiﬁer
     */
    public static void undeclaredIdentifier(int lineNum, int charNum) {
        ErrMsg.fatal(lineNum, charNum, "Undeclared identifier");
    }

    /**
     * Bad struct access (LHS of dot-access is not of a struct type)
     */
    public static void dotAccessOfNonStructType(int lineNum, int charNum) {
        ErrMsg.fatal(lineNum, charNum, "Dot-access of non-struct type");
    }

    /**
     * Bad struct access (RHS of dot-access is not a ﬁeld of the appropriate a
     * struct)
     */
    public static void invalidStructFieldName(int lineNum, int charNum) {
        ErrMsg.fatal(lineNum, charNum, "Invalid struct field name");
    }

    /**
     * Bad declaration (variable or parameter of type void)
     */
    public static void nonFunctionDeclaredVoid(int lineNum, int charNum) {
        ErrMsg.fatal(lineNum, charNum, "Non-function declared void");
    }

    /**
     * Bad declaration (attempt to declare variable of a bad struct type)
     */
    public static void invalidNameOfStructType(int lineNum, int charNum) {
        ErrMsg.fatal(lineNum, charNum, "Invalid name of struct type");
    }
}

// **********************************************************************
// <<<ProgramNode, DeclListNode, FormalsListNode, FnBodyNode,
// StmtListNode, ExpListNode>>>
// **********************************************************************

class ProgramNode extends ASTnode {
    public ProgramNode(DeclListNode L) {
        myDeclList = L;
        // build sym table
        this.table = new SymTable();
    }

    public void unparse(PrintWriter p, int indent) {
        myDeclList.unparse(p, indent);
    }

    /**
     * Name analyzer
     **/
    public void analyze() {
        this.myDeclList.analyze(this.table);
    }

    // 1 kid
    private DeclListNode myDeclList;
    private SymTable table;
}

class DeclListNode extends ASTnode {
    public DeclListNode(List<DeclNode> S) {
        myDecls = S;
    }

    public void unparse(PrintWriter p, int indent) {
        Iterator it = myDecls.iterator();
        try {
            while (it.hasNext()) {
                ((DeclNode) it.next()).unparse(p, indent);
            }
        } catch (NoSuchElementException ex) {
            System.err.println("unexpected NoSuchElementException in DeclListNode.print");
            System.exit(-1);
        }
    }

    /**
     * Analyze each declaration
     */
    public void analyze(SymTable table) {
        for (DeclNode decl : myDecls) {
            decl.analyze(table);
        }
    }

    // list of kids (DeclNodes)
    private List<DeclNode> myDecls;
}

class FormalsListNode extends ASTnode {
    public FormalsListNode(List<FormalDeclNode> S) {
        myFormals = S;
    }

    public void unparse(PrintWriter p, int indent) {
        Iterator<FormalDeclNode> it = myFormals.iterator();
        if (it.hasNext()) { // if there is at least one element
            it.next().unparse(p, indent);
            while (it.hasNext()) { // print the rest of the list
                p.print(", ");
                it.next().unparse(p, indent);
            }
        }
    }

    public void analyze(SymTable table) {
        for (FormalDeclNode formal : myFormals) {
            formal.analyze(table);
        }
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (FormalDeclNode formal : this.myFormals) {
            bld.append(formal.getType());
            if (this.myFormals.indexOf(formal) != this.myFormals.size() - 1) {
                bld.append(",");
            }
        }
        return bld.toString();
    }

    // list of kids (FormalDeclNodes)
    private List<FormalDeclNode> myFormals;
}

class FnBodyNode extends ASTnode {
    public FnBodyNode(DeclListNode declList, StmtListNode stmtList) {
        myDeclList = declList;
        myStmtList = stmtList;
    }

    public void unparse(PrintWriter p, int indent) {
        myDeclList.unparse(p, indent);
        myStmtList.unparse(p, indent);
    }

    public void analyze(SymTable table) {
        myDeclList.analyze(table);
        myStmtList.analyze(table);
    }

    // 2 kids
    private DeclListNode myDeclList;
    private StmtListNode myStmtList;
}

class StmtListNode extends ASTnode {
    public StmtListNode(List<StmtNode> S) {
        myStmts = S;
    }

    public void unparse(PrintWriter p, int indent) {
        Iterator<StmtNode> it = myStmts.iterator();
        while (it.hasNext()) {
            it.next().unparse(p, indent);
        }
    }

    public void analyze(SymTable table) {
        for (StmtNode stmt : myStmts) {
            stmt.analyze(table);
        }
    }

    // list of kids (StmtNodes)
    private List<StmtNode> myStmts;
}

class ExpListNode extends ASTnode {
    public ExpListNode(List<ExpNode> S) {
        myExps = S;
    }

    public void unparse(PrintWriter p, int indent) {
        Iterator<ExpNode> it = myExps.iterator();
        if (it.hasNext()) { // if there is at least one element
            it.next().unparse(p, indent);
            while (it.hasNext()) { // print the rest of the list
                p.print(", ");
                it.next().unparse(p, indent);
            }
        }
    }

    public void analyze(SymTable table) {
        for (ExpNode exp : myExps) {
            exp.analyze(table);
        }
    }

    // list of kids (ExpNodes)
    private List<ExpNode> myExps;
}

// **********************************************************************
// <<<DeclNode and its subclasses>>>
// **********************************************************************

abstract class DeclNode extends ASTnode {
    abstract IdNode getId();

    abstract String getType();

    abstract void analyze(SymTable table);

    public String getName() {
        return this.getId().getName();
    }
}

class VarDeclNode extends DeclNode {
    public VarDeclNode(TypeNode type, IdNode id, int size) {
        myType = type;
        myId = id;
        mySize = size;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        myType.unparse(p, 0);
        p.print(" ");
        myId.unparse(p, 0);
        p.println(";");
    }

    public void analyze(SymTable table) {
        // check if type is struct or other types
        if (this.mySize == VarDeclNode.NOT_STRUCT) {
            // not a struct
            // check if the variable has been declared or not
            this.myId.checkMultiplyDeclared(table);
            // check if the variable type is void (bad declaration)
            this.myId.checkNonFunctionDeclaredVoid(this.myType);
        } else {
            // struct
            // check if the variable has been declared or not
            this.myId.checkMultiplyDeclared(table);
            // check the name of the struct type has been previousely declared and is
            // actually he name of a struct type
            ((StructNode) this.myType).checkInvalidNameOfStructType(table);
        }

        try {
            table.addDecl(this.getName(), new Symb(this.getType(), Symb.VAR));
        } catch (DuplicateSymException e) {
            /**
             * Do not add duplicated symbol
             */
        } catch (Exception e) {
            System.err.println("Fatal: " + e.toString());
        }
    }

    public String getType() {
        return this.myType.getType();
    }

    public IdNode getId() {
        return this.myId;
    }

    // 3 kids
    private IdNode myId;
    private TypeNode myType;
    private int mySize; // use value NOT_STRUCT if this is not a struct type

    public static int NOT_STRUCT = -1;
}

class FnDeclNode extends DeclNode {
    public FnDeclNode(TypeNode type, IdNode id, FormalsListNode formalList, FnBodyNode body) {
        myType = type;
        myId = id;
        myFormalsList = formalList;
        myBody = body;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        myType.unparse(p, 0);
        p.print(" ");
        myId.unparse(p, 0);
        p.print("(");
        myFormalsList.unparse(p, 0);
        p.println(") {");
        myBody.unparse(p, indent + 4);
        p.println("}\n");
    }

    public void analyze(SymTable table) {
        // A function with the same name as another function in the same scope is
        // illegal
        boolean shouldProcessFormals;
        boolean shouldProcessBody;

        if (myId.checkMultiplyDeclared(table)) {
            // You must not add a new SymTable entry in the outer scope for this second
            // occurrence. You should process the formals and the local variables for both
            // the functions.
            Symb sym = table.lookupLocal(getName());
            if (sym.getKind().equals(Symb.FUNC)) {
                shouldProcessFormals = true;
                shouldProcessBody = true;
            } else {
                // A function with the same name as another variable in the same scope is
                // illegal. In this case, do not
                // create a SymTable entry for the function. However, continue processing the
                // body of the function .
                shouldProcessFormals = false;
                shouldProcessBody = true;
            }
        } else {
            shouldProcessFormals = true;
            shouldProcessBody = true;
            try {
                table.addDecl(this.getName(), new FnSymb(this.getType(), this.getName(), this.myFormalsList));
            } catch (Exception e) {
                System.err.println("Fatal: " + e.toString());
            }
        }

        table.addScope();

        if (shouldProcessFormals) {
            this.myFormalsList.analyze(table);
        }
        if (shouldProcessBody) {
            this.myBody.analyze(table);
        }

        // remove the new scope
        try {
            table.removeScope();
        } catch (Exception e) {
            System.err.println("Fatal: " + e.toString());
        }
    }

    public String getType() {
        return this.myType.getType();
    }

    public IdNode getId() {
        return this.myId;
    }

    // 4 kids
    private IdNode myId;
    private TypeNode myType;
    private FormalsListNode myFormalsList;
    private FnBodyNode myBody;
}

class FormalDeclNode extends DeclNode {
    public FormalDeclNode(TypeNode type, IdNode id) {
        myType = type;
        myId = id;
    }

    public void unparse(PrintWriter p, int indent) {
        myType.unparse(p, 0);
        p.print(" ");
        myId.unparse(p, 0);
    }

    public void analyze(SymTable table) {
        // check if the variable has been declared or not
        this.myId.checkMultiplyDeclared(table);
        // check if the variable type is void (bad declaration)
        this.myId.checkNonFunctionDeclaredVoid(this.myType);
        try {
            table.addDecl(this.getName(), new Symb(this.getType(), Symb.VAR));
        } catch (DuplicateSymException e) {
            /**
             * Do not add duplicated symbol
             */
        } catch (Exception e) {
            System.err.println("Fatal: " + e.toString());
        }
    }

    public String getType() {
        return this.myType.getType();
    }

    public IdNode getId() {
        return this.myId;
    }

    private IdNode myId;
    private TypeNode myType;
}

class StructDeclNode extends DeclNode {
    public StructDeclNode(IdNode id, DeclListNode declList) {
        myId = id;
        myDeclList = declList;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        p.print("struct ");
        myId.unparse(p, 0);
        p.println("{");
        myDeclList.unparse(p, indent + 4);
        addIndent(p, indent);
        p.println("};\n");

    }

    public void analyze(SymTable table) {
        if (myId.checkMultiplyDeclared(table)) {
            return;
        }
        try {
            table.addDecl(this.getName(), new StructSymb(TypeNode.STRUCT, getName(), this.myDeclList));
        } catch (Exception e) {
            System.err.print(e.toString());
        }

        table.addScope();
        this.myDeclList.analyze(table);
        try {
            table.removeScope();
        } catch (Exception e) {
            System.err.print(e.toString());
        }
    }

    public String getType() {
        return "struct";
    }

    public IdNode getId() {
        return this.myId;
    }

    private IdNode myId;
    private DeclListNode myDeclList;
}

// **********************************************************************
// <<<TypeNode and its Subclasses>>>
// **********************************************************************

abstract class TypeNode extends ASTnode {
    abstract String getType();

    public static String INT = "int";
    public static String BOOL = "bool";
    public static String VOID = "void";
    public static String STRUCT = "struct";
}

class IntNode extends TypeNode {
    public IntNode() {
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("int");
    }

    public String getType() {
        return "int";
    }
}

class BoolNode extends TypeNode {
    public BoolNode() {
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("bool");
    }

    public String getType() {
        return "bool";
    }
}

class VoidNode extends TypeNode {
    public VoidNode() {
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("void");
    }

    public String getType() {
        return "void";
    }
}

class StructNode extends TypeNode {
    public StructNode(IdNode id) {
        myId = id;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("struct ");
        myId.unparse(p, 0);
    }

    public boolean checkInvalidNameOfStructType(SymTable table) {
        Symb sym = table.lookupGlobal(this.myId.getName());
        if (sym == null || !sym.isStruct()) {
            ASTnode.invalidNameOfStructType(myId.getLineNum(), myId.getCharNum());
            return false;
        } else {
            return true;
        }
    }

    // 1 kid
    private IdNode myId;

    public String getType() {
        return myId.getName();
    }
}

// **********************************************************************
// <<<StmtNode and its subclasses>>>
// **********************************************************************

abstract class StmtNode extends ASTnode {
    abstract void analyze(SymTable table);

    abstract boolean isValid(SymTable table);

    abstract void handleNext(SymTable table);
}

class AssignStmtNode extends StmtNode {

    public AssignStmtNode(AssignNode assign) {
        myAssign = assign;
    }

    public void analyze(SymTable table) {
        myAssign.analyze(table);
    }

    public boolean isValid(SymTable table) {
        return myAssign.isValid(table);
    }

    /**
     * Nothing to be done
     */
    public void handleNext(SymTable table) {
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        myAssign.unparse(p, -1); // no parentheses
        p.println(";");
    }

    // 1 kid
    private AssignNode myAssign;
}

class PreIncStmtNode extends StmtNode {
    public PreIncStmtNode(ExpNode exp) {
        myExp = exp;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        p.print("++");
        myExp.unparse(p, 0);
        p.println(";");
    }

    public void analyze(SymTable table) {
        myExp.analyze(table);
    }

    public boolean isValid(SymTable table) {
        return myExp.isValid(table);
    }

    /**
     * Nothing to be done
     */
    public void handleNext(SymTable table) {
    }

    // 1 kid
    private ExpNode myExp;
}

class PreDecStmtNode extends StmtNode {
    public PreDecStmtNode(ExpNode exp) {
        myExp = exp;
    }

    public void analyze(SymTable table) {
        myExp.analyze(table);
    }

    public boolean isValid(SymTable table) {
        return myExp.isValid(table);
    }

    /**
     * Nothing to be done
     */
    public void handleNext(SymTable table) {
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        p.print("--");
        myExp.unparse(p, 0);
        p.println(";");
    }

    // 1 kid
    private ExpNode myExp;
}

class ReceiveStmtNode extends StmtNode {
    public ReceiveStmtNode(ExpNode e) {
        myExp = e;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        p.print("receive >> ");
        myExp.unparse(p, 0);
        p.println(";");
    }

    public void analyze(SymTable table) {
        myExp.analyze(table);
    }

    public boolean isValid(SymTable table) {
        return myExp.isValid(table);
    }

    /**
     * Nothing to be done
     */
    public void handleNext(SymTable table) {
    }

    // 1 kid (actually can only be an IdNode or an ArrayExpNode)
    private ExpNode myExp;
}

class PrintStmtNode extends StmtNode {
    public PrintStmtNode(ExpNode exp) {
        myExp = exp;
    }

    public void analyze(SymTable table) {
        myExp.analyze(table);
    }

    public boolean isValid(SymTable table) {
        return myExp.isValid(table);
    }

    /**
     * Nothing to be done
     */
    public void handleNext(SymTable table) {
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        p.print("print << ");
        myExp.unparse(p, 0);
        p.println(";");
    }

    // 1 kid
    private ExpNode myExp;
}

class IfStmtNode extends StmtNode {
    public IfStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
        myDeclList = dlist;
        myExp = exp;
        myStmtList = slist;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        p.print("if (");
        myExp.unparse(p, 0);
        p.println(") {");
        myDeclList.unparse(p, indent + 4);
        myStmtList.unparse(p, indent + 4);
        addIndent(p, indent);
        p.println("}");
    }

    public void analyze(SymTable table) {
        myExp.analyze(table);
    }

    public boolean isValid(SymTable table) {
        myExp.isValid(table);
    }

    public void handleNext(SymTable table) {
        table.addScope();
        myDeclList.analyze(table);
        try {
            table.removeScope();
        } catch (EmptySymTableException e) {
            System.err.println("Fatal Error");
            System.exit(-1);
        }

        table.addScope();
        myDeclList.analyze(table);
        try {
            table.removeScope();
        } catch (EmptySymTableException e) {
            System.err.println("Fatal Error");
            System.exit(-1);

        }
    }

    // e kids
    private ExpNode myExp;
    private DeclListNode myDeclList;
    private StmtListNode myStmtList;
}

class IfElseStmtNode extends StmtNode {
    public IfElseStmtNode(ExpNode exp, DeclListNode dlist1, StmtListNode slist1, DeclListNode dlist2,
            StmtListNode slist2) {
        myExp = exp;
        myThenDeclList = dlist1;
        myThenStmtList = slist1;
        myElseDeclList = dlist2;
        myElseStmtList = slist2;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        p.print("if (");
        myExp.unparse(p, 0);
        p.println(") {");
        myThenDeclList.unparse(p, indent + 4);
        myThenStmtList.unparse(p, indent + 4);
        addIndent(p, indent);
        p.println("}");
        addIndent(p, indent);
        p.println("else {");
        myElseDeclList.unparse(p, indent + 4);
        myElseStmtList.unparse(p, indent + 4);
        addIndent(p, indent);
        p.println("}");
    }

    // 5 kids
    private ExpNode myExp;
    private DeclListNode myThenDeclList;
    private StmtListNode myThenStmtList;
    private StmtListNode myElseStmtList;
    private DeclListNode myElseDeclList;
}

class WhileStmtNode extends StmtNode {
    public WhileStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
        myExp = exp;
        myDeclList = dlist;
        myStmtList = slist;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        p.print("while (");
        myExp.unparse(p, 0);
        p.println(") {");
        myDeclList.unparse(p, indent + 4);
        myStmtList.unparse(p, indent + 4);
        addIndent(p, indent);
        p.println("}");
    }

    // 3 kids
    private ExpNode myExp;
    private DeclListNode myDeclList;
    private StmtListNode myStmtList;
}

class RepeatStmtNode extends StmtNode {
    public RepeatStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
        myExp = exp;
        myDeclList = dlist;
        myStmtList = slist;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        p.print("repeat (");
        myExp.unparse(p, 0);
        p.println(") {");
        myDeclList.unparse(p, indent + 4);
        myStmtList.unparse(p, indent + 4);
        addIndent(p, indent);
        p.println("}");
    }

    // 3 kids
    private ExpNode myExp;
    private DeclListNode myDeclList;
    private StmtListNode myStmtList;
}

class CallStmtNode extends StmtNode {
    public CallStmtNode(CallExpNode call) {
        myCall = call;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        myCall.unparse(p, indent);
        p.println(";");
    }

    // 1 kid
    private CallExpNode myCall;
}

class ReturnStmtNode extends StmtNode {
    public ReturnStmtNode(ExpNode exp) {
        myExp = exp;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndent(p, indent);
        p.print("return");
        if (myExp != null) {
            p.print(" ");
            myExp.unparse(p, 0);
        }
        p.println(";");
    }

    // 1 kid
    private ExpNode myExp; // possibly null
}

// **********************************************************************
// <<<ExpNode and its subclasses>>>
// **********************************************************************

abstract class ExpNode extends ASTnode {
    abstract void analyze(SymTable table);

    abstract void isValid(SymTable table);

    abstract void handleNext(SymTable table);
}

class IntLitNode extends ExpNode {

    public void analyze(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public void isValid(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public void handleNext(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public IntLitNode(int lineNum, int charNum, int intVal) {
        myLineNum = lineNum;
        myCharNum = charNum;
        myIntVal = intVal;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print(myIntVal);
    }

    private int myLineNum;
    private int myCharNum;
    private int myIntVal;
}

class StringLitNode extends ExpNode {

    public void analyze(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public void isValid(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public void handleNext(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public StringLitNode(int lineNum, int charNum, String strVal) {
        myLineNum = lineNum;
        myCharNum = charNum;
        myStrVal = strVal;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print(myStrVal);
    }

    private int myLineNum;
    private int myCharNum;
    private String myStrVal;
}

class TrueNode extends ExpNode {

    public void analyze(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public void isValid(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public void handleNext(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public TrueNode(int lineNum, int charNum) {
        myLineNum = lineNum;
        myCharNum = charNum;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("true");
    }

    private int myLineNum;
    private int myCharNum;
}

class FalseNode extends ExpNode {

    public void analyze(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public void isValid(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public void handleNext(SymTable table) {
        /**
         * Literals, nothing to be analyzed
         */
    }

    public FalseNode(int lineNum, int charNum) {
        myLineNum = lineNum;
        myCharNum = charNum;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("false");
    }

    private int myLineNum;
    private int myCharNum;
}

class IdNode extends ExpNode {

    /**
     * For each IdNode in the AST that represents a use of a name Add a link to the
     * corresponding symbol-table entry.
     */
    public void analyze(SymTable table) {
        // check if this identifier has been declared or not
        isValid(table);

        // add a link to the corresponding symbol-table entry
        Symb sym = table.lookupLocal(getName());
        if (sym != null) {
            this.setLink(sym);
        }
    }

    /**
     * Check the uses of undeclared names
     */
    public void isValid(SymTable table) {
        if (table.lookupLocal(getName()) == null) {
            ASTnode.UndeclaredIdentifier(myLineNum, myCharNum);
        }
    }

    public void handleNext(SymTable table) {
        /**
         * No substructures, nothing to be done here
         */
    }

    public IdNode(int lineNum, int charNum, String strVal) {
        myLineNum = lineNum;
        myCharNum = charNum;
        myStrVal = strVal;
        link = null;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print(myStrVal);
    }

    public String getName() {
        return this.myStrVal;
    }

    public void setLink(Symb link) {
        this.link = link;
    }

    public Symb getLink() {
        return this.link;
    }

    public boolean checkMultiplyDeclared(SymTable table) {
        boolean isMultiplyDeclared = table.lookupLocal(this.myStrVal) != null;

        if (isMultiplyDeclared) {
            ASTnode.multiplyDeclaredIdentifier(this.myLineNum, this.myCharNum);
        }

        return isMultiplyDeclared;
    }

    public boolean checkUndeclared(SymTable table) {
        boolean isUndeclared = table.lookupGlobal(this.myStrVal) == null;

        if (isUndeclared) {
            ASTnode.undeclaredIdentifier(this.myLineNum, this.myCharNum);
        }

        return isUndeclared;
    }

    public boolean checkNonFunctionDeclaredVoid(TypeNode type) {
        boolean isVoid = type.getType().equals(TypeNode.VOID);

        if (isVoid) {
            ASTnode.nonFunctionDeclaredVoid(this.myLineNum, this.myCharNum);
        }

        return isVoid;
    }

    public int getLineNum() {
        return this.myLineNum;
    }

    public int getCharNum() {
        return this.myCharNum;
    }

    private int myLineNum;
    private int myCharNum;
    private String myStrVal;
    // add a "link" to the corresponding symbol-table entry.
    private Symb link;
}

class DotAccessExpNode extends ExpNode {

    /**
     */
    public void analyze(SymTable table) {
        myLoc.analyze(table);
        myId.analyze(table);
        isValid(table);
    }

    /**
     * A bad struct access happens when either the left-hand side of the dot-access
     * is not a name already declared to be of a struct type or the right-hand side
     * of the dot-access is not the name of a ﬁeld for the appropriate type of
     * struct.
     */
    public void isValid(SymTable table) {
        // check if myLoc has been declared
    }

    public void handleNext(SymTable table) {
    }

    public DotAccessExpNode(ExpNode loc, IdNode id) {
        myLoc = loc;
        myId = id;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLoc.unparse(p, 0);
        p.print(").");
        myId.unparse(p, 0);
    }

    // 2 kids
    private ExpNode myLoc;
    private IdNode myId;
}

class AssignNode extends ExpNode {
    public AssignNode(ExpNode lhs, ExpNode exp) {
        myLhs = lhs;
        myExp = exp;
    }

    public void unparse(PrintWriter p, int indent) {
        if (indent != -1)
            p.print("(");
        myLhs.unparse(p, 0);
        p.print(" = ");
        myExp.unparse(p, 0);
        if (indent != -1)
            p.print(")");
    }

    // 2 kids
    private ExpNode myLhs;
    private ExpNode myExp;
}

class CallExpNode extends ExpNode {
    public CallExpNode(IdNode name, ExpListNode elist) {
        myId = name;
        myExpList = elist;
    }

    public CallExpNode(IdNode name) {
        myId = name;
        myExpList = new ExpListNode(new LinkedList<ExpNode>());
    }

    // ** unparse **
    public void unparse(PrintWriter p, int indent) {
        myId.unparse(p, 0);
        p.print("(");
        if (myExpList != null) {
            myExpList.unparse(p, 0);
        }
        p.print(")");
    }

    // 2 kids
    private IdNode myId;
    private ExpListNode myExpList; // possibly null
}

abstract class UnaryExpNode extends ExpNode {
    public UnaryExpNode(ExpNode exp) {
        myExp = exp;
    }

    public void analyze(SymTable table) {
        this.myExp.analyze(table);
    }

    public void isValid(SymTable table) {
    }

    public void handleNext(SymTable table) {
    }

    // one child
    protected ExpNode myExp;
}

abstract class BinaryExpNode extends ExpNode {
    public BinaryExpNode(ExpNode exp1, ExpNode exp2) {
        myExp1 = exp1;
        myExp2 = exp2;
    }

    public void analyze(SymTable table) {
        this.myExp1.analyze(table);
        this.myExp2.analyze(table);
    }

    public void isValid(SymTable table) {
    }

    public void handleNext(SymTable table) {
    }

    // two kids
    protected ExpNode myExp1;
    protected ExpNode myExp2;
}

// **********************************************************************
// <<<Subclasses of UnaryExpNode>>>
// **********************************************************************

class UnaryMinusNode extends UnaryExpNode {

    public UnaryMinusNode(ExpNode exp) {
        super(exp);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(-");
        myExp.unparse(p, 0);
        p.print(")");
    }
}

class NotNode extends UnaryExpNode {
    public NotNode(ExpNode exp) {
        super(exp);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(!");
        myExp.unparse(p, 0);
        p.print(")");
    }
}

// **********************************************************************
// <<<Subclasses of BinaryExpNode>>>
// **********************************************************************

class PlusNode extends BinaryExpNode {
    public PlusNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" + ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class MinusNode extends BinaryExpNode {
    public MinusNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" - ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class TimesNode extends BinaryExpNode {
    public TimesNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" * ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class DivideNode extends BinaryExpNode {
    public DivideNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" / ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class AndNode extends BinaryExpNode {
    public AndNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" && ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class OrNode extends BinaryExpNode {
    public OrNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" || ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class EqualsNode extends BinaryExpNode {
    public EqualsNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" == ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class NotEqualsNode extends BinaryExpNode {
    public NotEqualsNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" != ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class LessNode extends BinaryExpNode {
    public LessNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" < ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class GreaterNode extends BinaryExpNode {
    public GreaterNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" > ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class LessEqNode extends BinaryExpNode {
    public LessEqNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" <= ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class GreaterEqNode extends BinaryExpNode {

    public GreaterEqNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" >= ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}
