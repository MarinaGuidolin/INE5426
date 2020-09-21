import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

	static public String extractFileName(String filePath) {
		String fileName = "";
		int i = filePath.lastIndexOf('/');
		int j = filePath.lastIndexOf('.');
		if (i > 0) {
			fileName = filePath.substring(i + 1, j);
		}
		return fileName;
	}

	static public void exportTokens(String filePath, CommonTokenStream tokens,
			HashMap<String, SymbolTableEntry> symbolTable) throws IOException {
		ArrayList<String> listTokens = new ArrayList<>();

		for (Token token : tokens.getTokens()) {
			int type = token.getType();

			String typeName = CC2020Lexer.VOCABULARY.getSymbolicName(type);
			String lexem = token.getText();

			int line = token.getLine();
			int startIndex = token.getStartIndex();
			int stopIndex = token.getStopIndex();
			int tokenIndex = token.getTokenIndex();

			String stringOfToken = "";

			if (symbolTable.containsKey(lexem)) {
				stringOfToken = String.format(
						"\nToken #%d: <key=%s, type=%s, lexem=%s, line=%d, startIndex=%d, stopIndex=%d\n", tokenIndex,
						lexem, typeName, lexem, line, startIndex, stopIndex);
			} else {
				stringOfToken = String.format("\nToken #%d: <type=%s, lexem=%s, line=%d, startIndex=%d, stopIndex=%d\n",
						tokenIndex, typeName, lexem, line, startIndex, stopIndex);
			}

			listTokens.add(stringOfToken);
		}

		writeTokens(filePath, listTokens);
	}

	static public void writeTokens(String filePath, ArrayList<String> tokens) throws IOException {
		String fileName = extractFileName(filePath);

		String newFileName = fileName + ".txt";

		String directoryPath = createDirectory("/Out/Tokens/", filePath);

		String newFilePath = directoryPath + newFileName;

		File newFile = new File(newFilePath);

		FileWriter writer = new FileWriter(newFile);

		writer.write("============> Tokens from the file " + fileName + ".ccc\n\n");

		for (String s : tokens) {
			writer.write(s);
		}

		System.out.println("Os tokens do arquivo " + fileName + ".ccc se encontram no arquivo: " + newFilePath);

		writer.close();
	}

	static public void exportSymbolTable(String filePath, HashMap<String, SymbolTableEntry> symbolTable)
			throws IOException {
		String fileName = extractFileName(filePath);

		String newFileName = fileName + ".txt";

		String directoryPath = createDirectory("/Out/SymbolTable/", filePath);

		String newFilePath = directoryPath + newFileName;

		File newFile = new File(newFilePath);

		FileWriter writer = new FileWriter(newFile);

		writer.write("============> Symbol Table from the file " + fileName + ".ccc\n\n");

		for (String key : symbolTable.keySet()) {
			String s = String.format("Entry: <key=%s, %s>\n", key, symbolTable.get(key).getValues());
			writer.write(s);
		}

		System.out
				.println("A tabela de símbolos do arquivo " + fileName + ".ccc se encontra no arquivo: " + newFilePath);

		writer.close();
	}

	static public String createDirectory(String name, String filePath) throws IOException {
		File file = new File(".");

		String fullPath = file.getCanonicalPath();

		String directoryPath = fullPath + name;

		File dir = new File(directoryPath);

		if (!dir.exists()) {

			dir.mkdirs();
		}

		return directoryPath;
	}

	public static HashMap<String, SymbolTableEntry> createSymbolTable(CommonTokenStream tokens) {
		HashMap<String, SymbolTableEntry> symbolTable = new HashMap<String, SymbolTableEntry>();

		for (Token t : tokens.getTokens()) {
			int type = t.getType();
			String typeName = CC2020Lexer.VOCABULARY.getSymbolicName(type);

			if (typeName.equals("IDENT")) {
				String lexem = t.getText();
				if (!symbolTable.containsKey(lexem)) {
					SymbolTableEntry entry = new SymbolTableEntry(lexem);
					symbolTable.put(lexem, entry);
				}
			}

		}

		return symbolTable;
	}
}
