grammar ConvCC20201;

@header {
import ExpressionTree.*;
import Scope.*;
import SymbolTable.*;
import Enum.*;
import GCI.*;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.exit;
}

@parser::members {
    List<ExpressionTree> trees = new ArrayList<ExpressionTree>();

    List<ScopeNode> scopes = new ArrayList<ScopeNode>();

    LinkedStack scopeStack = new LinkedStack();

    int temporaryCounter, labelCounter, funcCounter, beginCounter, exprTrueCounter, exprFalseCounter = 0;

    ExpressionTree addTree(Node node) {
        ExpressionTree expTree = new ExpressionTree(node, temporaryCounter);
        Node root = expTree.getRoot();
        String result = expTree.validateTree(root);
        if (result.equals("")) {
            String msg = "The tree below contains invalid expressions\n" + expTree.getRoot().toString();
            notifyErrorListeners("Invalid expression detected");
            throw new ParseCancellationException('\n' + msg);
        }

        trees.add(expTree);
        return expTree;
    }

    String createLabel(String type) {
        String s = "";
        switch (type) {
            case "label": {
                s = "L_" + labelCounter;
                labelCounter++;
                break;
            }
            case "func": {
                s = "FUNC_" + funcCounter;
                funcCounter++;
                break;
            }
            case "begin": {
                s = "BEGIN_" + beginCounter;
                beginCounter++;
                break;
            }
            case "exprTrue": {
                s = "EXPR_TRUE_" + exprTrueCounter;
                exprTrueCounter++;
                break;
            }
            case "exprFalse": {
                s = "EXPR_FALSE_" + exprFalseCounter;
                exprFalseCounter++;
                break;
            }
        }
        return s;
    }

    GenerateReturn generateCode(ExpressionTree expTree) {
        StringBuilder sb = new StringBuilder();
        Node root = expTree.getRoot();
        GenerateReturn generateReturn = new GenerateReturn(expTree.generateCode(root, sb), sb.toString());
        temporaryCounter = expTree.getCounter();
        return generateReturn;
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

    void verifyBreak() {
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
    locals [String code]
@init {
    {putScope(false);}
    $code = "";
}
@after {
    popScope();
    Utils.exportExpressionTrees(trees);
    Utils.exportIntermediaryCode($code);
    Utils.exportScopes(scopes);
    Log.success("SUCESS", "Well done! All arithmetic expressions are valid");
    Log.success("SUCESS", "Well done! All declared variables are valid");
    Log.success("SUCESS", "Well done! All break commands are contained within a for statement");
}
:
	statement[false, createLabel("label"), ""] {$code = $statement.code ;}
	| funclist {$code = $funclist.code;}
	|
;

funclist
    returns [String code]
    @init {
        $code = "";
    }
:

	funcdef funclist2 {$code = $funcdef.code + "\n" +  $funclist2.code;}
;

funclist2
    returns [String code]
    @init {
        $code = "";
    }
:
	funclist {$code = $funclist.code;}
	|
;

funcdef
    returns [String code]
    locals [String funcLabel, String next]
    @init {
        $code = "";
        $funcLabel = createLabel("func");
        $next = createLabel("label");
    }
:
	 DEF IDENT {insertIdent($IDENT.text, true, "function", $IDENT.line);} {putScope(false);} LPAREN paramlist RPAREN LBRACE statelist[false, $next, ""] RBRACE {popScope();}
     {$code = $funcLabel + ":\n" + $statelist.code;}
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

statement[boolean isFor, String next, String breakNext]
    returns [String code]
    @init {
        $code = "";
    }
:

	vardecl SEMI
	| atribstat1 SEMI {$code = $atribstat1.code;}
	| printstat SEMI
	| readstat SEMI 
	| returnstat SEMI
	| ifstat[isFor, next, breakNext] {$code = $ifstat.code;}
	| forstat[next] {$code = $forstat.code;}
	| LBRACE {putScope(isFor);} statelist[isFor, next, breakNext] RBRACE {popScope();} {$code = $statelist.code;}
	| BREAK {verifyBreak();} {$code = Generator.generateInconditionalDeviationCode($breakNext);}SEMI
	| SEMI
;

statelist[boolean isFor, String next, String breakNext]
    returns [String code]
    locals [String stmtNext]
    @init {
        $stmtNext = createLabel("label");
    }
:

	statement[isFor, $stmtNext, breakNext] statelist2[isFor, next, breakNext]
	{$code = $statement.code + "\n" + $stmtNext + ":\n" +  $statelist2.code ;}
;

statelist2[boolean isFor, String next, String breakNext]
    returns [String code]
    @init {
        $code = "";
    }
:

	statelist[isFor, next, breakNext] {$code = $statelist.code;}
	|
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

	PRINT expression[""]
;

readstat:

	READ lvalue[""]
;

returnstat:

	RETURN
;

ifstat[boolean isFor, String next, String breakNext]
    returns [String code]
    locals [String exprTrue, String exprFalse]
    @init {
        $exprTrue = createLabel("exprTrue");
        $exprFalse = createLabel("exprFalse");
    }
:
	IF LPAREN expression[$exprFalse] RPAREN LBRACE {putScope(isFor);} statelist[isFor, next, breakNext] RBRACE {popScope();} ifstat1[isFor, next, breakNext]
	{
	$code = $expression.code
	        + $exprTrue + ":\n"
	        + $statelist.code
	        + Generator.generateInconditionalDeviationCode(next)
	        + $exprFalse + ":\n"
	        + $ifstat1.code;
	}
;

ifstat1[boolean isFor, String next, String breakNext]
    returns [String code]
    @init {
        $code = "";
    }
:

	ELSE statement[isFor, next, breakNext] {$code = $statement.code;}
	|
;

forstat[String next]
    returns [String code]
    locals[String begin, String exprTrue]
    @init {
        $begin = createLabel("begin");
        $exprTrue = createLabel("exprTrue");
    }
:

	FOR LPAREN atribstat1 SEMI expression[next] SEMI atribstat1 RPAREN statement[true, $begin, next]
	{
	$code = $begin + ":\n"
	    + $expression.code
	    + $exprTrue + ":\n"
	    + $statement.code
	    + Generator.generateInconditionalDeviationCode($begin);
	}
;

allocexpression:

	NEW allocexpression1
;

allocexpression1:

	TYPE_INT t2 b[null]
	| TYPE_FLOAT t2 b[null]
	| TYPE_STRING t2 b[null]
;

atribstat1
    returns [String code]
    @init {
        $code = "";
    }
:

	lvalue[""] ASSIGN atribstat2
	{
	    if (!$atribstat2.code.isEmpty()) {
	        $code = $lvalue.code + $atribstat2.code + $lvalue.last + $ASSIGN.text + $atribstat2.last;
	    }
	}
;


atribstat2
    returns [String code, String last]
    locals [ExpressionTree expTree]

    @init {
        $code = "";
        $last = "";
        $expTree = new ExpressionTree();
    }
:

	IDENT atribstat3[new Node($IDENT.text, getTypeOfIdent($IDENT.text))] {$code = $atribstat3.code; $last= $atribstat3.last;}

	| allocexpression

	| ADD factor[$ADD.text] d[$factor.sin] c[$d.sin] expression2 {$expTree = addTree($c.sin);}

	{
	    GenerateReturn generateReturn = generateCode($expTree);
	    $code = generateReturn.getCode();
	    $last = generateReturn.getLast();
	}

	| SUB factor[$SUB.text] d[$factor.sin] c[$d.sin] expression2 {$expTree = addTree($c.sin);}

	{
	    GenerateReturn generateReturn = generateCode($expTree);
	    $code = generateReturn.getCode();
	    $last = generateReturn.getLast();
	}

	| INT d[new Node($INT.text, "int")] c[$d.sin] expression2 {$expTree = addTree($c.sin);}

	{
	    GenerateReturn generateReturn = generateCode($expTree);
	    $code = generateReturn.getCode();
	    $last = generateReturn.getLast();
	}

  	| FLOAT d[new Node($FLOAT.text, "float")] c[$d.sin] expression2 {$expTree = addTree($c.sin);}

	{
	    GenerateReturn generateReturn = generateCode($expTree);
	    $code = generateReturn.getCode();
	    $last = generateReturn.getLast();
	}

  	| STRING d[new Node($STRING.text, "string")] c[$d.sin] expression2 {$expTree = addTree($c.sin);}

	{
	    GenerateReturn generateReturn = generateCode($expTree);
	    $code = generateReturn.getCode();
	    $last = generateReturn.getLast();
	}

	| NULL d[null] c[null] expression2

	| LPAREN numexpression RPAREN d[$numexpression.sin] c[$d.sin] expression2 {$expTree = addTree($c.sin);}

	{
	    GenerateReturn generateReturn = generateCode($expTree);
	    $code = generateReturn.getCode();
	    $last = generateReturn.getLast();
	}
;

atribstat3[Node h]
    returns [String code, String last]
    locals [ExpressionTree expTree]
    @init {
            $code = "";
            $last = "";
            $expTree = new ExpressionTree();
    }
:
	b[h] d[$b.sin] c[$d.sin] expression2 {$expTree = addTree($c.sin);}

	{
	    GenerateReturn generateReturn = generateCode($expTree);
	    $code = generateReturn.getCode();
	    $last = generateReturn.getLast();
	}

	|   LPAREN paramlistcall RPAREN

    {
        String[] params = $paramlistcall.text.split(",");
        String[] result = Generator.generateFunctionCallCode(h.getValue(), params);
        $code = result[0];
        $last = result[1];
    }
;

b[Node type]
    returns [Node sin]
:
	t2 b[type] {$sin = new Node("array", $t2.sin, $b.sin);}
	| {$sin = type;}
;

t2
    returns[Node sin]
:
	LBRACK numexpression RBRACK {$sin = $numexpression.sin;}
;

expression[String exprFalse]
    returns [String code]
:

	numexpression expression2 {$code = Generator.generateConditionalDeviationCode("False " + $numexpression.last + $expression2.code, exprFalse);}
; 

expression2
    returns [String code]
    @init {
        $code = "";
    }
:

	GT numexpression {$code = $GT.text + $numexpression.last;}
	| LT numexpression {$code = $LT.text + $numexpression.last;}
	| LE numexpression {$code = $LE.text + $numexpression.last;}
	| GE numexpression {$code = $GE.text + $numexpression.last;}
	| EQUAL numexpression {$code = $EQUAL.text + $numexpression.last;}
	| NOTEQUAL numexpression {$code = $NOTEQUAL.text + $numexpression.last;}
	|
; 

numexpression
    returns [Node sin, String code, String last]
    locals [ExpressionTree expTree]
:
	term c[$term.sin] {$sin = $c.sin; $expTree = addTree($sin);}
	{
	    GenerateReturn generateReturn = generateCode($expTree);
        $code = generateReturn.getCode();
        $last = generateReturn.getLast();
    }
;

c[Node h]
    returns [Node sin]
:
	t3[h] c[$t3.sin] {$sin = $c.sin;}
	| {$sin = h;}
;

t3[Node h]
    returns [Node sin]
:
	ADD term {$sin = new Node($ADD.text, h, $term.sin);}
	| SUB term {$sin = new Node($SUB.text, h, $term.sin);}
;

term
    returns [Node sin]
:
	 unaryexpr d[$unaryexpr.sin] {$sin = $d.sin;}
;

d[Node h]
    returns [Node sin]
:
	t4[h] d[$t4.sin] {$sin = $d.sin;}
	| {$sin = h;}
;

t4[Node h]
    returns [Node sin]
:
	MUL unaryexpr {$sin = new Node($MUL.text, h, $unaryexpr.sin);}
	| DIV unaryexpr {$sin = new Node($DIV.text, h, $unaryexpr.sin);}
	| MOD unaryexpr {$sin = new Node($MOD.text, h, $unaryexpr.sin);}
;

unaryexpr
    returns [Node sin]
:
	ADD factor[$ADD.text] {$sin = $factor.sin;}
	| SUB factor[$SUB.text] {$sin = $factor.sin;}
	| factor[""] {$sin = $factor.sin;}
;

factor[String h]
    returns [Node sin]
:
	INT {$sin = new Node(h + $INT.text, "int");}
	| FLOAT {$sin = new Node(h + $FLOAT.text, "float");}
	| STRING {$sin = new Node($STRING.text, "string");}
	| NULL
	| lvalue[h] {$sin = $lvalue.node;}
	| LPAREN numexpression RPAREN {$sin = $numexpression.sin;}
;

lvalue[String h]
    returns [Node node, String code, String last]
    locals [ExpressionTree expTree]
:
	 IDENT b[new Node(h + $IDENT.text, getTypeOfIdent($IDENT.text))] {$node = $b.sin; $expTree = addTree($node);}
	 {
        GenerateReturn generateReturn = generateCode($expTree);
        $code = generateReturn.getCode();
        $last = generateReturn.getLast();
	 }
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
