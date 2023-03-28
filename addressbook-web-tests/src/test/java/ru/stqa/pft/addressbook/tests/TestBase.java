package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app;

  static {
    try {
      app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeSuite
          //foreMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  //rMethod(alwaysRun = true)
  public void tearDown()
          //throws Exception
  {
    app.stop();

  }

}
