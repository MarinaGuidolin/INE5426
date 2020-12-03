grammar ConvCC20201;

@header {
import ExpressionTree.*;
import Scope.*;
import SymbolTable.*;
import Enum.*;
import static java.lang.System.exit;
}

@parser::members {
    List<ExpressionTree> trees = new ArrayList<ExpressionTree>();

    List<ScopeNode> scopes = new ArrayList<ScopeNode>();

    LinkedStack scopeStack = new LinkedStack();

    void addTree(Node node) {
        ExpressionTree expTree = new ExpressionTree(node);
        Node root = expTree.getRoot();
        String result = expTree.postOrder(root);
        if (result.equals("")) {
            String msg = "The tree below contains invalid expressions\n" + expTree.getRoot().toString();
            notifyErrorListeners("Invalid expression detected");
            throw new ParseCancellationException('\n' + msg);
        }
        trees.add(expTree);
    }

    void insertIdent(String lexeme, boolean isFunction, String type, int declarationLine) {
        ScopeNode actualScope = scopeStack.peek();
        SemanticReports report = actualScope.getSymbolTable().insert(lexeme, isFunction, type, declarationLine);
        if (report == SemanticReports.IDENT_ALREADY_EXISTS) {
            int previousDeclarationLine = actualScope.getSymbolTable().getEntry(lexeme).getDeclarationLine();
            String msg = "The variable " + lexeme + " has been declared previously at line " + previousDeclarationLine;
            notifyErrorListeners(msg);
            throw new ParseCancellationException('\n' + msg);
        }
    }

    void putScope(boolean isFor) {
        scopeStack.push(new SymbolTable(), isFor);
    }

    void popScope() {
        scopes.add(scopeStack.pop());
    }

    void verifyBreak(int line) {
        ScopeNode actualScope = scopeStack.peek();
        if (!actualScope.isFor()) {
            String msg = "A break command was written outside a for statement";
            notifyErrorListeners(msg);
            throw new ParseCancellationException('\n' + msg);
        }
    }

    String getTypeOfIdent(String lexeme) {
        ScopeNode previous = scopeStack.peek();
        while (previous != null) {
             SymbolTableEntry entry = previous.getSymbolTable().getEntry(lexeme);
             if (entry != null) {
                return entry.getType();
             }
             previous = previous.getPrevious();
        }
        String msg = "The identifier " + lexeme + " was used but not defined";
        notifyErrorListeners(msg);
        throw new ParseCancellationException('\n' + msg);
    }
}

program
@after {
    Utils.exportExpressionTrees(trees);
    //for (ScopeNode n: scopes) {
        //System.out.println(n.toString());
    //}
    Log.success("SUCESS", "Well done! All arithmetic expressions are valid");
    Log.success("SUCESS", "Well done! All declared variables are valid");
    Log.success("SUCESS", "Well done! All break commands are contained within a for statement");
}
:
 	{putScope(false);} (statement[false]
	| funclist)?
	{popScope();}
;

funclist: 

	funcdef funclist2 
;

funclist2:  

	funclist?
;

funcdef:
	 DEF IDENT {insertIdent($IDENT.text, true, "function", $IDENT.line);} LPAREN {putScope(false);} paramlist RPAREN LBRACE statelist[false] RBRACE {popScope();}
;

paramlist:

	TYPE_INT IDENT paramlist2 {insertIdent($IDENT.text, false, $TYPE_INT.text, $IDENT.line);}
	| TYPE_FLOAT IDENT paramlist2 {insertIdent($IDENT.text, false, $TYPE_FLOAT.text, $IDENT.line);}
	| TYPE_STRING IDENT paramlist2 {insertIdent($IDENT.text, false, $TYPE_STRING.text, $IDENT.line);}
	|
;

paramlist2:

	COMMA paramlist
	|

;

statement[boolean isFor]:

	vardecl SEMI 
	| atribstat1 SEMI
	| printstat SEMI
	| readstat SEMI 
	| returnstat SEMI
	| ifstat[isFor]
	| forstat 
	| LBRACE {putScope(isFor);} statelist[isFor] RBRACE {popScope();}
	| BREAK {verifyBreak($BREAK.line);} SEMI
	| SEMI
;

vardecl:

	TYPE_INT IDENT a {insertIdent($IDENT.text, false, $TYPE_INT.text, $IDENT.line);}
	| TYPE_FLOAT IDENT a {insertIdent($IDENT.text, false, $TYPE_FLOAT.text, $IDENT.line);}
	| TYPE_STRING IDENT a {insertIdent($IDENT.text, false, $TYPE_STRING.text, $IDENT.line);}
;

a:
	t1 a
	|
; 

t1: 
	LBRACK INT RBRACK;

paramlistcall:

	IDENT paramlistcall2
	|
;

paramlistcall2:

	COMMA paramlistcall
	|
; 

printstat:

	PRINT expression
;

readstat:

	READ lvalue[' ']
;

returnstat:

	RETURN
;

ifstat[boolean isFor]:

	IF LPAREN expression RPAREN LBRACE {putScope(isFor);} statelist[isFor] RBRACE {popScope();} ifstat1[isFor]
;

ifstat1[boolean isFor]:

	ELSE statement[isFor]
	|
;

forstat:

	FOR LPAREN atribstat1 SEMI expression SEMI atribstat1 RPAREN statement[true]
;

statelist[boolean isFor]:

	statement[isFor] statelist2[isFor]
;

statelist2[boolean isFor]:

	statelist[isFor]
	|
;	

allocexpression:

	NEW allocexpression1
;

allocexpression1:

	TYPE_INT t2 b[null]
	| TYPE_FLOAT t2 b[null]
	| TYPE_STRING t2 b[null]
;

atribstat1:

	lvalue[' '] ASSIGN atribstat2
;


atribstat2:

	IDENT atribstat3[new Node($IDENT.text, getTypeOfIdent($IDENT.text))]
	| allocexpression
	| ADD factor['+'] d[$factor.sin] c[$d.sin] expression2 {addTree($c.sin); }
	| SUB factor['-'] d[$factor.sin] c[$d.sin] expression2 {addTree($c.sin); }
	| INT d[new Node($INT.text, "int")] c[$d.sin] expression2 {addTree($c.sin); }
  	| FLOAT d[new Node($FLOAT.text, "float")] c[$d.sin] expression2 {addTree($c.sin);}
  	| STRING d[new Node($STRING.text, "string")] c[$d.sin] expression2 {addTree($c.sin);}
	| NULL d[null] c[null] expression2
	| LPAREN numexpression RPAREN d[$numexpression.sin] c[$d.sin] expression2 {addTree($c.sin);}
;

 atribstat3[Node h]
 :
	b[h] d[$b.sin] c[$d.sin] expression2 {addTree($c.sin);}
	|   LPAREN paramlistcall RPAREN
;

b[Node h] returns [Node sin]
:
	t2 b[h] {$sin = new Node("array", $t2.sin, $b.sin);}
	| {$sin = h;}
;

t2 returns[Node sin]
:
	LBRACK numexpression RBRACK {$sin = $numexpression.sin;}
;

expression
:

	numexpression expression2
; 

expression2:

	GT numexpression
	| LT numexpression
	| LE numexpression
	| GE numexpression
	| EQUAL numexpression
	| NOTEQUAL numexpression
	|
; 

numexpression returns [Node sin]
:
	term c[$term.sin] {$sin = $c.sin;} {addTree($sin);}
;

c[Node h] returns [Node sin]
:
	t3[h] c[$t3.sin] {$sin = $c.sin;}
	| {$sin = h;}
;

t3[Node h] returns [Node sin]
:
	ADD term {$sin = new Node($ADD.text, h, $term.sin);}
	| SUB term {$sin = new Node($SUB.text, h, $term.sin);}
;

term returns [Node sin]
:
	 unaryexpr d[$unaryexpr.sin] {$sin = $d.sin;}
;

d[Node h] returns [Node sin]
:
	t4[h] d[$t4.sin] {$sin = $d.sin;}
	| {$sin = h;}
;

t4[Node h] returns [Node sin]
:
	MUL unaryexpr {$sin = new Node($MUL.text, h, $unaryexpr.sin);}
	| DIV unaryexpr {$sin = new Node($DIV.text, h, $unaryexpr.sin);}
	| MOD unaryexpr {$sin = new Node($MOD.text, h, $unaryexpr.sin);}
;

unaryexpr returns [Node sin]
:
	ADD factor['+'] {$sin = $factor.sin;}
	| SUB factor['-'] {$sin = $factor.sin;}
	| factor[' '] {$sin = $factor.sin;}
;

factor[char h] returns [Node sin]
:
	INT {$sin = new Node(h + $INT.text, "int");}
	| FLOAT {$sin = new Node(h + $FLOAT.text, "float");}
	| STRING {$sin = new Node($STRING.text, "string");}
	| NULL
	| lvalue[h] {$sin = $lvalue.node;}
	| LPAREN numexpression RPAREN {$sin = $numexpression.sin;}
;

lvalue[char h] returns [Node node]
:
	 IDENT b[new Node(h + $IDENT.text, getTypeOfIdent($IDENT.text))] {$node = $b.sin;}
;

/*
 * Keywords
 */
IF: 'if';
FOR: 'for';
ELSE: 'else';
RETURN: 'return';
READ: 'read';
PRINT: 'print';
NEW: 'new';
BREAK: 'break';
DEF: 'def';

/*
 * Types
 */
TYPE_STRING: 'string';
TYPE_FLOAT: 'float';
TYPE_INT: 'int';
NULL: 'null';

INT: DIGITS;
FLOAT: DIGITS '.' DIGITS | '.' DIGITS;

fragment DIGITS: [0-9]+;

STRING: '"' ~('"')* '"';
IDENT: [a-zA-Z_] ( [a-zA-Z0-9_] )*;

/*
 * Symbols and Punctuation
*/

LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
LBRACK : '[';
RBRACK : ']';
SEMI : ';';
COMMA : ',';

/*
 * Arithmetic Operators
*/

ASSIGN : '=';
ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
MOD : '%';

/*
* Relational Operators
*/

GT : '>';
LT : '<';
EQUAL : '==';
LE : '<=';
GE : '>=';
NOTEQUAL : '!=';

WHITESPACE: [ \t\r\n]+ -> skip;
