package telran.performance;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import telran.text.*;

public class JoinStringsPerformanceAppl {

	private static final int N_STRINGS = 1000;
	private static final int N_RUNS = 1000;
	private static final String BASE_PACKAGE = "telran.text.";

	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println("There should be 3 arguments <name of class as an argument>");
		} else {
			String[] strings = getStrings();
			JoinStrings[] joinStringsArray = new JoinStrings[args.length];
			for (int i = 0; i < args.length; i++) {
				@SuppressWarnings("unchecked")
				Class<JoinStrings> clazz = (Class<JoinStrings>) Class.forName(BASE_PACKAGE + args[i]);
				Constructor<JoinStrings> constructor = clazz.getConstructor();
				joinStringsArray[i] = constructor.newInstance();
			}
			PerformanceTest[] performanceTestArray = getTest(args, strings, joinStringsArray);
			for (PerformanceTest test : performanceTestArray) {
				test.run();
			}
		}
	}

	private static PerformanceTest[] getTest(String[] className, String[] strings, JoinStrings[] joinStringsArray) {
		PerformanceTest[] array = new PerformanceTest[joinStringsArray.length];
		for (int i = 0; i < array.length; i++) {
			String testName = getTestName(className[i]);
			array[i] = new JoinStringsPerformanceTest(testName, N_RUNS, strings, joinStringsArray[i]);
		}
		return array;
	}

	private static String getTestName(String className) {

		return String.format("%s; Number of the strings is %d", className, N_STRINGS);
	}

	private static String[] getStrings() {
		String[] res = new String[N_STRINGS];
		Arrays.fill(res, "string");
		return res;
	}

}