package generated_tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;
import java.text.*;

public class TestRunner {
	public static void main(String[] args) {
		SimpleDateFormat formatter;
		Locale currentLocale = new Locale("en","US");
		formatter = new SimpleDateFormat("H:mm:ss:SSS", currentLocale);

		System.out.println("Starting tests at " + formatter.format(new Date()));
		Result result = JUnitCore.runClasses(JunitTestSuite.class);
		System.out.println("Tests finishes at " + formatter.format(new Date()));
		System.out.format("Report :: total run = %d, failures = %d, in time = %d milliseconds\n\n\n",
			result.getRunCount(),
			result.getFailureCount(),
			result.getRunTime()
		);

		for (Failure failure : result.getFailures()) {
			System.err.println(failure.getDescription().toString());
			failure.getException().printStackTrace();
		}
   }
}

