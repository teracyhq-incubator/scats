package org.teracy.scats.core.actions;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.teracy.scats.utils.TestLogger;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class ManageFile extends ScenarioSteps{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PageCore pc;
	/**
	 * set clipboard
	 * @param string
	 */
	@Step
	private void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	/**
	 * upload file using robot
	 * @param fileLocation
	 */
	@Step
	public void uploadFileUsingRobot(String fileLocation) {
		try {
			//Setting clip board with file location
			setClipboardData(fileLocation);
			//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	/**
	 * upload file using javascript
	 * @param element
	 * @param fileLocation
	 * @throws InterruptedException
	 */
	@Step
	public void uploadFileUsingJavascript(String fileLocation) throws InterruptedException{
		TestLogger.info("Attach a file");
		String fs = File.separator;
		WebElement elem=pc.waitForGetElementPresent("//*[@type='file']");
		fileLocation=System.getProperty("user.dir")+fileLocation.replace("/", fs).replace("\\", fs);
		TestLogger.info(fileLocation);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.display = 'block';", elem);
		elem.sendKeys(fileLocation);
		Thread.sleep(5000);
	}

	/**
	 * get height of image
	 * @param path
	 * @return
	 */
	@Step
	public Integer getImageWidth(String path) {
		String fs = File.separator;
		path=System.getProperty("user.dir")+path.replace("/", fs).replace("\\", fs);
		BufferedImage readImage = null;
		Integer height=null;
		try {
			readImage = ImageIO.read(new File(path));
			height = readImage.getHeight();
		} catch (Exception e) {
			readImage = null;
		}
		return height;
	}

	/**
	 * get width of image
	 * @param path
	 * @return
	 */
	@Step
	public Integer getImageHeight(String path) {
		String fs = File.separator;
		path=System.getProperty("user.dir")+path.replace("/", fs).replace("\\", fs);
		BufferedImage readImage = null;
		Integer width=null;
		try {
			readImage = ImageIO.read(new File(path));
			width = readImage.getWidth();
		} catch (Exception e) {
			readImage = null;
		}
		return width;
	}
}
