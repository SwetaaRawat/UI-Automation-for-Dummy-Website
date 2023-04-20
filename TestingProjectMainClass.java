package fyndProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;




public class TestingProjectMainClass {
	public WebDriver driver;

	@BeforeMethod
	public void launchApp() throws Exception {
		// Creating FileReader object to identify note pad file
		FileInputStream f = new FileInputStream("C:\\Users\\Shweta\\Desktop\\projectData.xls");

		// opening identified excel sheet or workbook
		Workbook wb = Workbook.getWorkbook(f);

		// Open the sheet in a opened workbook
		Sheet s = wb.getSheet("Sheet1");

		System.setProperty(s.getCell(0, 0).getContents(), s.getCell(0, 1).getContents());
		

		driver = new ChromeDriver();
		driver.get(s.getCell(0, 2).getContents());
		driver.manage().window().maximize();
		Thread.sleep(20000);

	}

	@Test
	public void availabilityOfEachTab() {
		// Creating page object model class object
		PageObjectModelOfProject p = PageFactory.initElements(driver, PageObjectModelOfProject.class);

		boolean homeTabAvailible = p.homeTab.isDisplayed() && p.homeTab.isEnabled();
		boolean footballTabAvailible = p.footballTab.isDisplayed() && p.footballTab.isEnabled();
		boolean busketballTabAvailible = p.busketballTab.isDisplayed() && p.busketballTab.isEnabled();
		boolean kriketTabAvailible = p.kriketTab.isDisplayed() && p.kriketTab.isEnabled();
		boolean cibersportTabAvailible = p.cibersportTab.isDisplayed() && p.cibersportTab.isEnabled();

		// Implementing soft Assertion
		SoftAssert s = new SoftAssert();
		s.assertEquals(homeTabAvailible, true);
		s.assertEquals(footballTabAvailible, true);
		s.assertEquals(busketballTabAvailible, true);
		s.assertEquals(kriketTabAvailible, true);
		s.assertEquals(cibersportTabAvailible, true);
		System.out.println("Test Scenario f1 pass ho gya");
	}

	@Test
	public void urlOfEachTabContainsTabName() throws Exception {
		PageObjectModelOfProject p = PageFactory.initElements(driver, PageObjectModelOfProject.class);

		String homeURL = driver.getCurrentUrl();
		boolean homeTabContainshome = homeURL.contains("Home");

		p.footballTab.click();
		String footballURL = driver.getCurrentUrl();
		boolean footballContainsFootball = footballURL.contains("football");

		p.busketballTab.click();
		String busketballURL = driver.getCurrentUrl();
		boolean busketballContainsBusketball = busketballURL.contains("busketball");

		p.kriketTab.click();
		String kriketURL = driver.getCurrentUrl();
		boolean kriketContainsKriket = kriketURL.contains("kriket");

		p.cibersportTab.click();
		String cibersportURL = driver.getCurrentUrl();
		boolean cibersportContainscibersport = cibersportURL.contains("cibersport");

		System.out.println("homeTabContainshome: " + homeTabContainshome);
		System.out.println("footballContainsFootball " + footballContainsFootball);
		System.out.println("busketballContainsBusketball " + busketballContainsBusketball);
		System.out.println("kriketContainsKriket " + kriketContainsKriket);
		System.out.println("cibersportContainscibersport " + cibersportContainscibersport);

		// Implementing soft Assertion
		SoftAssert s = new SoftAssert();

		try {
			s.assertEquals(homeTabContainshome, true);
		} catch (AssertionError e) {
			System.out.println(e);
		}

		try {
			s.assertEquals(footballContainsFootball, true);
		} catch (AssertionError e) {
			System.out.println(e);
		}

		try {
			s.assertEquals(busketballContainsBusketball, true);
		} catch (AssertionError e) {
			System.out.println(e);
		}

		try {
			s.assertEquals(kriketContainsKriket, true);
		} catch (AssertionError e) {
			System.out.println(e);
		}

		try {
			s.assertEquals(cibersportContainscibersport, true);
		} catch (AssertionError e) {
			System.out.println(e);
		}

		Thread.sleep(2000);

        //Store all the current URL in excel sheet
		FileOutputStream fo = new FileOutputStream("C:\\\\Users\\\\Shweta\\\\Desktop\\\\ProjectURL.xls");
		WritableWorkbook wb = Workbook.createWorkbook(fo);
		WritableSheet ws = wb.createSheet("Result", 1);

		Label L1 = new Label(0, 0, "homeURL: " + homeURL);
		Label L2 = new Label(0, 1, "footballURL: " + footballURL);
		Label L3 = new Label(0, 2, "busketballURL: " + busketballURL);
		Label L4 = new Label(0, 3, "kriketURL: " + kriketURL);
		Label L5 = new Label(0, 4, "cibersportURL: " + cibersportURL);

		// Adding cell into the sheet
		ws.addCell(L1);
		ws.addCell(L2);
		ws.addCell(L3);
		ws.addCell(L4);
		ws.addCell(L5);

		// saving excel
		wb.write();
		wb.close();
		s.assertAll();
	}

	@AfterMethod
	public void closeApp() {
		driver.close();
	}
}
