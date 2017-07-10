package runner;

import java.io.IOException;
import org.junit.Test;

import teracy.coreactions.PageCore;
import teracy.utils.FileUtil;
import teracy.utils.SplitTestRunner;

public class GenerateParallelTest {
	@Test
	public void generateMultiTestRunnerFile() throws IOException {
		SplitTestRunner sTestRunner = new SplitTestRunner();
		String inputStr = "package runner;" + "\n" + "import net.serenitybdd.cucumber.CucumberWithSerenity;" + "\n"
				+ "import org.junit.runner.RunWith;" + "\n" + "import cucumber.api.CucumberOptions;" + "\n"
				+ "@RunWith(CucumberWithSerenity.class)" + "\n" + "@CucumberOptions(" + "\n"
				+ "features={\"src/test/resources/tempfeatures/testrunner${index}\"}," + "\n"
				+ "glue={\"scenariosteps\",\"teracy.coresteps\"}," + "\n"
				+ "//tags = {\"@smoke\",\"~@firefox\",\"~@fail\"}," + "\n"
				+ "plugin={\"junit:target/testrunner${index}/cucumber.xml\"" + "\n"
				+ "		,\"html:target/testrunner${index}\"" + "\n"
				+ "		,\"json:target/testrunner${index}/cucumber.json\"}" + "\n" + ")" + "\n"
				+ "public class TestRunner${index} {" + "\n" + "}";
		String pathTestRunner = System.getProperty("user.dir") + "/src/test/java/runner/";
		String pathFile = pathTestRunner + "TestRunner${index}.java";
		String tempFeatures = System.getProperty("user.dir") + "/src/test/resources/tempfeatures";
		String soureFeature = System.getProperty("user.dir") + "/src/test/resources/features";
		FileUtil.deleteFilesForPathByPrefix(pathTestRunner, "TestRunner");
		FileUtil.deleteDirectory(tempFeatures);
		for (int i = 1; i <= Integer.valueOf(PageCore.getConfig("thread.number")); i++) {
			FileUtil.writeFile(inputStr.replace("${index}", String.valueOf(i)),
					pathFile.replace("${index}", String.valueOf(i)));
		}
		sTestRunner.splitTest(soureFeature, tempFeatures);
	}

}
