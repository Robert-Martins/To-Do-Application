package test;

import models.Comment;
import models.Job;
import models.Workspace;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class JobTest {

    @Test
    public void TesteConstrutorWorkspace(){
        var comment = new Comment("comment");

        var comments = new ArrayList<Comment>();

        comments.add(comment);

        Job teste = new Job("Job de teste", "desc", new Date());

        teste.setComments(comments);

        assertEquals("Job de teste", teste.getName());
        assertEquals("comment", teste.getComments().get(0).getComment());
    }

}
