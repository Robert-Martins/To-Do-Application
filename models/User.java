package models;

import java.util.Date;
import java.util.List;

public class User {

    private String name;

    private List<Workspace> workspaces;

    private Date updateAt;

    private Date createdAt;

    public User(String name, List<Workspace> workspaces) {
        this.setName(name);
        this.setWorkspaces(workspaces);
        this.setUpdateAt(new Date());
        this.setCreatedAt(new Date());
    }

    public User(String name){
        this.setName(name);
        this.setUpdateAt(new Date());
        this.setCreatedAt(new Date());
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Workspace> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<Workspace> workspaces) {
        this.workspaces = workspaces;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
