package fyndProject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObjectModelOfProject {
	//Creating page object model
         @FindBy(xpath= "//a[text()='Home']") WebElement homeTab;
         @FindBy(xpath= "//a[text()='Football']") WebElement footballTab;
         @FindBy(xpath= "//a[text()='Busketball']") WebElement busketballTab;
         @FindBy(xpath= "//a[text()='Kriket']") WebElement kriketTab;
         @FindBy(xpath= "//a[text()='Cibersport']") WebElement cibersportTab;
         
	

}
