package GCI;

import java.util.ArrayList;

public class Generator {
	public static String generateThreeAddressCode(String addr1, String addr2, String addr3, String operator) {
		return String.format("%s = %s %s %s\n", addr1, addr2, operator, addr3);
	}

	public static String generateCopyCode(String addr1, String addr2) {
		return String.format("%s = %s\n", addr1, addr2);
	}

	public static String generateIndexedCopyCode(String addr1, String addr2, String index, boolean leftIsIndexed) {
		if (leftIsIndexed) {
			return String.format("%s[%s] = %s\n", addr1, index, addr2);
		} else {
			return String.format("%s = %s[%s]\n", addr1, addr2, index);
		}
	}

	public static String generateInconditionalDeviationCode(String label) {
		return String.format("goto %s\n", label);
	}

	public static String generateConditionalDeviationCode(String expression, String label) {
		return String.format("if %s goto %s\n", expression, label);
	}

	public static String[] generateFunctionCallCode(String functioName, String[] params) {
		StringBuilder sb = new StringBuilder();
		for (String p: params) {
			sb.append("param " + p + '\n');
		}
		String[] result = {sb.toString(), String.format("call %s,%d", functioName, params.length)};
		return result;
	}
}
