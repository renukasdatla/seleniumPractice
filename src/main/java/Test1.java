import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver signUp=null;
        try {
            System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver_win32_renu\\chromedriver.exe");
            signUp = new ChromeDriver();

            signUp.get("https://www.facebook.com");
            enterTextToAField(signUp,"xpath","//input[@name='firstname']","Renuka");
            enterTextToAField(signUp,"xpath","//input[@name='lastname']","Mantena");
            enterTextToAField(signUp,"xpath","//input[@aria-label='Mobile number or email']","robert1sam@gmail.com");
            enterTextToAField(signUp,"xpath","//input[@aria-label='Re-enter email']","robert1sam@gmail.com");
            enterTextToAField(signUp,"xpath","//input[@aria-label='New password']","Notreachable");

            selectFromDropDown(signUp,"id","month","Jan");
            selectFromDropDown(signUp,"id","day","9");
            selectFromDropDown(signUp,"id","year","2001");

            click(signUp,"xpath","(//input[@name='sex'])[1]");
            //signUp.findElement(By.xpath("(//input[@name='sex'])[1]")).click();
            click(signUp,"xpath","//button[@name='websubmit']");
            //signUp.findElement(By.xpath("//button[@name='websubmit']")).click();
            waitUntil_VisibilityOfElement_Found(signUp,20,"//a[text()='Send Email Again']","xpath");
            String success_text = signUp.findElement(By.xpath("//*[contains(text(),'Enter the code from')and(@class='uiHeaderTitle')]")).getText();

            if (success_text.contains("Enter the code from your email")){
                System.out.println("test case success");
            }

            Thread.sleep(1000);
            signUp.close();
        }catch (Exception e){
            System.out.println("test case Failed------------------------------------>");
            e.printStackTrace();
            //signUp.close();
        }

    }

    public static void click(WebDriver currentClassDriver, String locatorType,String locator){
        if (locatorType.equalsIgnoreCase("xpath")){
            currentClassDriver.findElement(By.xpath(locator)).click();
        }else if (locatorType.equalsIgnoreCase("id")){
            currentClassDriver.findElement(By.id(locator)).click();
        }else {
            new Exception("please provide xpath or Id locators");
        }
    }

    public static void selectFromDropDown(WebDriver currentClassDriver, String locatorType,String locator,String locatorValue){
        if (locatorType.equalsIgnoreCase("xpath")){
            Select Sel = new Select(currentClassDriver.findElement(By.xpath(locator)));
            Sel.selectByVisibleText(locatorValue);
        }else if (locatorType.equalsIgnoreCase("id")){
            Select Sel = new Select(currentClassDriver.findElement(By.id(locator)));
            Sel.selectByVisibleText(locatorValue);
        }else {
            new Exception("please provide xpath or Id locators");
        }
    }

    public static void enterTextToAField(WebDriver currentClassDriver, String locatorType,String locatorValue,String textToBeEntered){
        if (locatorType.equalsIgnoreCase("xpath")){
            currentClassDriver.findElement(By.xpath(locatorValue)).sendKeys(textToBeEntered);
        }else if (locatorType.equalsIgnoreCase("id")){
            currentClassDriver.findElement(By.id(locatorValue)).sendKeys(textToBeEntered);
        }else {
            new Exception("please provide xpath or Id locators");
        }
    }

    public static void waitUntil_VisibilityOfElement_Found(WebDriver w,int time,String xpath,String locatorType){
        WebDriverWait wait = new WebDriverWait(w,time);
        if (locatorType.equalsIgnoreCase("xpath")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        }else if(locatorType.equalsIgnoreCase("id")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(xpath)));
        }
        else if(locatorType.equalsIgnoreCase("classname")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(xpath)));
        }else{
            new Exception("Invalid Locator Type provided by programer");
        }
    }
}
