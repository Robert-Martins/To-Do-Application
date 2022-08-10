package models;

import java.util.List;

public class User {

    private List<Workspace> workspaces;

    public User(List<Workspace> workspaces) {
        this.setWorkspaces(workspaces);
    }

    public List<Workspace> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<Workspace> workspaces) {
        this.workspaces = workspaces;
    }
}
