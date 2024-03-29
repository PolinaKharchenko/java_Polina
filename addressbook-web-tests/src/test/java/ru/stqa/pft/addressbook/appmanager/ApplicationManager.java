package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    protected WebDriver wd;
  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) throws IOException {
    this.browser = browser;
    properties = new Properties();
  }
  public void init() throws IOException {
      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win10")));
      dbHelper = new DbHelper();

    if("".equals(properties.getProperty("selenium.server"))){
    if (Objects.equals(browser, BrowserType.FIREFOX)){
    wd = new FirefoxDriver();}
    else if (Objects.equals(browser, BrowserType.CHROME)) {
      wd = new ChromeDriver();
    }
    else if ( Objects.equals(browser, BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }}
    else {
        wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities) ;
    }


    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper (wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

  }

 // public void logout() {
  //  wd.findElement(By.linkText("Logout")).click();
  //}
  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
  public DbHelper db(){
        return dbHelper;
  }




}
