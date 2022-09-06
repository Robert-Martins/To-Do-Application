package test;

import java.util.ArrayList;
import org.junit.Test;
import models.User;
import models.Workspace;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testConstrutorUser() {

        var workspace = new Workspace("workspace", "desc");

        var workspaces = new ArrayList<Workspace>();

        workspaces.add(workspace);

        User teste = new User("Usuario de teste", workspaces);

        assertEquals("Usuario de teste", teste.getName());
        assertEquals("workspace", teste.getWorkspaces().get(0).getName());
    }
    
}
