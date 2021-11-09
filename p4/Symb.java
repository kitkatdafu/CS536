import java.util.*;

public class Symb {
    private String type;
    private String kind;

    public static final String STRUCT = "struct";
    public static final String FUNC = "func";
    public static final String VAR = "var";

    public Symb(String type, String kind) {
        this.type = type;
        this.kind = kind;
    }

    public boolean isStruct() {
        return this.kind.equals(STRUCT);
    }

    public String getKind() {
        return this.kind;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return type;
    }
}

class FnSymb extends Symb {

    private FormalsListNode formals;
    private String fnName;

    public FnSymb(String type, String fnName, FormalsListNode formals) {
        super(type, Symb.FUNC);
        this.fnName = fnName;
        this.formals = formals;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();

        bld.append(this.formals.toString());
        bld.append("->");
        bld.append(this.getType());

        return bld.toString();

    }
}

class StructSymb extends Symb {

    private String structName;
    private DeclListNode structFields;
    private HashMap<String, Symb> fields;

    public StructSymb(String type, String structName, DeclListNode structFields) {
        super(type, Symb.STRUCT);
        this.structName = structName;
        this.structFields = structFields;
        this.fields = null;
    }

    public void setFields(HashMap<String, Symb> fields) {
        this.fields = fields;
    }

    public Symb getField(String name) {
        return this.fields.get(name);
    }

    public boolean containsField(IdNode fieldIdNode) {
        return this.structFields.contains(fieldIdNode.getName());
    }
}