package test;

import models.Job;
import models.User;
import models.Workspace;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class WorkspaceTest {

    @Test
    public void TesteConstrutorWorkspace(){
        var job = new Job("job", "desc", new Date());

        var jobs = new ArrayList<Job>();

        jobs.add(job);

        Workspace teste = new Workspace("Workspace de teste", "desc");

        teste.setJobs(jobs);

        assertEquals("Workspace de teste", teste.getName());
        assertEquals("job", teste.getJobs().get(0).getName());
    }

}
