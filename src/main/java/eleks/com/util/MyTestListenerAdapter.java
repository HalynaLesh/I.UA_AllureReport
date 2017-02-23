package eleks.com.util;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import eleks.com.webdriver.WebDriverFactory;

public class MyTestListenerAdapter extends TestListenerAdapter {

	@Step
	@Override
	public void onTestFailure(ITestResult result) {
		takeScreenShot(result.getTestName());
	}

	@Step
	@Override
	public void onTestSkipped(ITestResult result) {
		takeScreenShot(result.getTestName());

	}

	@Step
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		takeScreenShot(result.getTestName());
	}

	@Attachment
	private byte[] takeScreenShot(String methodName) {
		return ((TakesScreenshot) WebDriverFactory.getInstance())
				.getScreenshotAs(OutputType.BYTES);
	}

	@Override
	public void onTestStart(ITestResult result) {

		FileWriter fw;
		 
		Runtime runtime = Runtime.getRuntime();
		DateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(result.getStartMillis());
		try {
			InetAddress address = InetAddress.getLocalHost();
			fw = new FileWriter("src/main/resources/environment.properties");
			fw.write("Web: http://i.ua \n");
			fw.write("OSname: " + System.getProperty("os.name") + "\n");
			fw.write("OSversion: " + System.getProperty("os.version") + "\n");
			fw.write("OSbyte: " + System.getProperty("os.arch") + "\n");
			fw.write("UserName: Halyna Leshchyshyn" + "\n");
			fw.write("Position: QA" + "\n");
			fw.write("HostName: " + System.getProperty("user.name")+"\n");
			
			fw.write("Started:" + formatter.format(calendar.getTime()) + "\n");
			
			fw.write("HostAddress: "+ address.getHostAddress() + "\n");
			fw.write("Host Name: "+ address.getHostName() + "\n");
			fw.write("CanonicalHostName: "+ address.getCanonicalHostName() + "\n");
			//fw.write("Address: "+ address.getAddress() + "\n");
			fw.write("LocalHost: "+ address.getLocalHost() + "\n");
			fw.write("LoopbackAddress: "+ address.getLoopbackAddress() + "\n");
			fw.write("JavaVersion: " + System.getProperty("java.runtime.version") + "\n");
			fw.write("TotalMemory: "+ Runtime.getRuntime().totalMemory()+"\n");
			fw.write("UsageMemory: "+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())+"\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
