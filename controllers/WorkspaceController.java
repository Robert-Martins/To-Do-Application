package controllers;

import models.Workspace;

import java.util.Date;
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
                .orElseThrow();
    }

    public void updateWorkspace(UUID uuid, String name, String description){
        var user = userController.getUser();
        var workspaces = userController.getUser().getWorkspaces();
        var workspace = workspaces.stream()
                .filter(w -> w.getUuid() == uuid)
                .findAny()
                .orElseThrow();
        workspaces.remove(workspace);
        workspace.setName(name);
        workspace.setDescription(description);
        workspace.setUpdatedAt(new Date());
        workspaces.add(workspace);
        user.setWorkspaces(workspaces);
    }

    public void deleteWorkspace(UUID uuid){
        var user = userController.getUser();
        var workspaces = userController.getUser().getWorkspaces();
        workspaces.remove(this.getWorkspace(uuid));
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

}
