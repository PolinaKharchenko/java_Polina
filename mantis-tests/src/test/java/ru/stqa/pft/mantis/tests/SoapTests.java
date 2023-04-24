package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {
    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFix(1);
        System.out.println("The test is running");
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project: projects){
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException{
        skipIfNotFix(1);
        System.out.println("The test is running");
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("test issue")
                .withDescription("test issue description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void testIssueCorrect() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFix(1);
        System.out.println("The test is running");
    }
}
