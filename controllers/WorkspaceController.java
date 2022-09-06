package controllers;

import exceptions.ResourceNotFoundException;
import models.Job;
import models.Workspace;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WorkspaceController {

    private UserController userController = new UserController();

    public WorkspaceController(){}

    public Workspace createWorkspace(String name, String description){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        if(workspaces == null)
            workspaces = new ArrayList<>();
        var workspace = new Workspace(name, description);
        workspaces.add(workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return workspace;
    }

    public Workspace getWorkspace(int index){
        return userController.getUser().getWorkspaces().get(index);
    }

    public List<Job> getWorkspaceJobs(int index){
        return this.getWorkspace(index).getJobs();
    }

    public Workspace updateWorkspace(int id, String name, String description){
        var user = userController.getUser();
        var workspaces = userController.getUser().getWorkspaces();
        var workspace = this.getWorkspace(id);
        workspace.setName(name);
        workspace.setDescription(description);
        workspace.setUpdatedAt(new Date());
        workspaces.set(id, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return workspace;
    }

    public void deleteWorkspace(int index){
        var user = userController.getUser();
        var workspaces = userController.getUser().getWorkspaces();
        var workspace = getWorkspace(index);
        workspaces.remove(index);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

}
