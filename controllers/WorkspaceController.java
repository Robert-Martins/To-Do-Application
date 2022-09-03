package controllers;

import exceptions.ResourceNotFoundException;
import models.Job;
import models.Workspace;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WorkspaceController {

    final UserController userController;

    WorkspaceController(UserController userController){
        this.userController = userController;
    }

    public void createWorkspace(String name, String description){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        workspaces.add(new Workspace(name, description));
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

    public Workspace getWorkspace(UUID uuid){
        return userController.getUser().getWorkspaces().stream()
                .filter(workspace -> workspace.getUuid() == uuid)
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Workspace Not Found"));
    }

    public List<Job> getWorkspaceJobs(UUID uuid){
        return this.getWorkspace(uuid).getJobs();
    }

    public void updateWorkspace(UUID uuid, String name, String description){
        var user = userController.getUser();
        var workspaces = userController.getUser().getWorkspaces();
        var workspace = this.getWorkspace(uuid);
        var index = workspaces.indexOf(workspace);
        workspace.setName(name);
        workspace.setDescription(description);
        workspace.setUpdatedAt(new Date());
        workspaces.set(index, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

    public void deleteWorkspace(UUID uuid){
        var user = userController.getUser();
        var workspaces = userController.getUser().getWorkspaces();
        workspaces.remove(this.getWorkspace(uuid));
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

}
