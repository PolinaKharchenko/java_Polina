package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));


  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

  public boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("api.baseUrl")));
    IssueData issue = mc.mc_issue_get(app.getProperty("api.adminLogin"), app.getProperty("api.adminpassword"), BigInteger.valueOf(issueId));
   if((issue.getStatus().getName().equals("close"))||(issue.getStatus().getName().equals("resolved"))){return false;}
   else return true;
     }


  public void skipIfNotFix(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    if(isIssueOpen(issueId)){
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}




