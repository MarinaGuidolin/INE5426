import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Utils {
	
    static public String extractFileExtension(String filePath) {
        String extension = "";
        int i = filePath.lastIndexOf('.');
        if (i > 0) {
            extension = filePath.substring(i + 1);
        }
        return extension;
    }
    
    static public void exportTokens(String filePath, CommonTokenStream tokens) throws IOException {
    	ArrayList<String> listTokens = new ArrayList<>();
    	
    	for (Token token : tokens.getTokens()) {
    		int type = token.getType();
    		
    		String typeName = CC2020Lexer.VOCABULARY.getSymbolicName(type);
    		String lexem = token.getText();
    		
    		int line = token.getLine();
    		int startIndex = token.getStartIndex();
    		int stopIndex = token.getStopIndex();
    		
    	    String stringToken = String.format("\nToken: <type=%s, lexem='%s', line=%d, startIndex=%d, stopIndex=%d>\n", typeName, lexem, line, startIndex, stopIndex);
    	    
    	    listTokens.add(stringToken);
    	}
    	
    	writeTokens(filePath, listTokens);
    }
    
    static public String extractFileName(String filePath) {
    	String fileName = "";
    	int i = filePath.lastIndexOf('/');
    	int j = filePath.lastIndexOf('.');
    	if (i > 0) {
    		fileName = filePath.substring(i + 1,j);
    	}
    	return fileName;
    }
    
    static public void writeTokens(String filePath, ArrayList<String> tokens) throws IOException {
    	String fileName = extractFileName(filePath);
    	String newFileName = fileName + "_tokens.txt";
    	FileWriter writer = new FileWriter(newFileName);
    	for (String s : tokens) {
    		writer.write(s);
    	}
    	System.out.println("Os tokens do arquivo " + fileName + " se encontram no arquivo: " + newFileName);
    	writer.close();
    }
}
