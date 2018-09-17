package runner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.TestNG;

public class Runner {

	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.setProperty("current.date", dateFormat.format(new Date()));

		TestNG runner = new TestNG();
		List<String> suitefiles = new ArrayList<String>();
		suitefiles
				.add("d:\\Volha_Rasokha\\ATM2018Q2\\workspace\\module13\\src\\test\\resources\\resources\\testng.xml");
		runner.setTestSuites(suitefiles);
		runner.run();
	}

}
