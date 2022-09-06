package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import models.Comment;
import models.Job;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommentTest {
	
	Date dataAtual = new Date();
	UUID uuid = new UUID(1,1);

    @Test
    public void testConstrutorComment() {

        Comment teste = new Comment("comment");

        assertEquals("comment", teste.getComment());
        
    }
    
}
