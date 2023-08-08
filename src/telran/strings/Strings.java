package telran.strings;

public class Strings {
static public String javaVariable() {
	
	return "[a-zA-Z$][\\w$]*|_[\\w$]+";
}
static public String zero_300() {
	
	return "[1-9]\\d?|[1-2]\\d\\d|0|300";
}
static public String ipV4Octet() {
	
	return "(\\d\\d?|[0-1]\\d\\d|2([0-4]\\d|5[0-5]))";
}
static public String ipV4Address(){
	String octet = ipV4Octet();
	//return String.format("%s(\\.%s){3}", octet, octet);
	return String.format("%1$s(\\.%1$s){3}", octet);
}
static public String arithmeticExpression() {
	String operandRe = operand();
	String operatorRe = operator();
	return String.format("%1$s(%2$s%1$s)*",operandRe, operatorRe);
}
private static String operator() {
	return "[-+*/]";
}
private static String operand() {
	String numberExp = numberExp();
	String variableExp = javaVariable();
	return String.format("\\s*((%s|%s))\\s*", numberExp, variableExp);
}
private static String numberExp() {
	
	return "(\\d+\\.?\\d*|\\.\\d+)";
}
}
