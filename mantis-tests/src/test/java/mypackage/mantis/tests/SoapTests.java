package mypackage.mantis.tests;

import mypackage.mantis.model.Issue;
import mypackage.mantis.model.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {
    @Test
    public void testGetProjecs() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(0000001);
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue()
                .withSummary("Test issue").withDescription("Test description")
                .withProject(projects.iterator().next());
        Issue newIssue = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), newIssue.getSummary());
    }
}
