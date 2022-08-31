package controllers;

import models.Job;

import java.util.Date;
import java.util.UUID;

public class JobController {

    final UserController userController;

    final WorkspaceController workspaceController;

    public JobController(UserController userController, WorkspaceController workspaceController){
        this.userController = userController;
        this.workspaceController = workspaceController;
    }

    public void createJob(UUID workspaceUuid, String name, String description, Date dueDate){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        workspaces.remove(workspace);
        var jobs = workspace.getJobs();
        jobs.add(new Job(name, description, dueDate));
        workspace.setJobs(jobs);
    }

    public Job getJob(UUID workspaceUuid, UUID uuid){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        return workspace.getJobs().stream()
                .filter(job -> job.getUuid() == uuid)
                .findAny()
                .orElseThrow();
    }

    public void updateJob(UUID workspaceUuid, UUID uuid, String name, String description, Date dueDate){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var jobs = workspace.getJobs();
        var job = this.getJob(workspaceUuid, uuid);
        workspaces.remove(workspace);
        jobs.remove(job);
        job.setName(name);
        job.setDescription(description);
        job.setDueDate(dueDate);
        jobs.add(job);
        workspace.setJobs(jobs);
        workspaces.add(workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

    public void deleteJob(UUID workspaceUuid, UUID uuid){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        workspaces.remove(workspace);
        var jobs = workspace.getJobs();
        jobs.remove(this.getJob(workspaceUuid, uuid));
        workspace.setJobs(jobs);
        workspaces.add(workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

}
