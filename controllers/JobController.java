package controllers;

import enums.JobStatus;
import models.Comment;
import models.Job;

import java.util.Date;
import java.util.List;
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
        var index = workspaces.indexOf(workspace);
        var jobs = workspace.getJobs();
        jobs.add(new Job(name, description, dueDate));
        workspace.setJobs(jobs);
        workspaces.set(index, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
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

    public List<Comment> getJobComments(UUID workspaceUuid, UUID uuid){
        var job = this.getJob(workspaceUuid, uuid);
        return job.getComments();
    }

    public void updateJob(UUID workspaceUuid, UUID uuid, String name, String description, Date dueDate){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var jobs = workspace.getJobs();
        var job = this.getJob(workspaceUuid, uuid);
        var workspaceIndex = workspaces.indexOf(workspace);
        var index = jobs.indexOf(job);
        job.setName(name);
        job.setDescription(description);
        job.setDueDate(dueDate);
        job.setUpdatedAt(new Date());
        jobs.set(index, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceIndex, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

    public void jobToToDo(UUID workspaceUuid, UUID uuid){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var jobs = workspace.getJobs();
        var job = this.getJob(workspaceUuid, uuid);
        var workspaceIndex = workspaces.indexOf(workspace);
        var index = jobs.indexOf(job);
        job.setStatus(JobStatus.TO_DO);
        job.setUpdatedAt(new Date());
        jobs.set(index, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceIndex, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

    public void jobToDevelopment(UUID workspaceUuid, UUID uuid){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var jobs = workspace.getJobs();
        var job = this.getJob(workspaceUuid, uuid);
        var workspaceIndex = workspaces.indexOf(workspace);
        var index = jobs.indexOf(job);
        job.setStatus(JobStatus.DEVELOPMENT);
        job.setUpdatedAt(new Date());
        jobs.set(index, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceIndex, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

    public void jobToStandby(UUID workspaceUuid, UUID uuid){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var jobs = workspace.getJobs();
        var job = this.getJob(workspaceUuid, uuid);
        var workspaceIndex = workspaces.indexOf(workspace);
        var index = jobs.indexOf(job);
        job.setStatus(JobStatus.STAND_BY);
        job.setUpdatedAt(new Date());
        jobs.set(index, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceIndex, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

    public void jobToDone(UUID workspaceUuid, UUID uuid){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var jobs = workspace.getJobs();
        var job = this.getJob(workspaceUuid, uuid);
        var workspaceIndex = workspaces.indexOf(workspace);
        var index = jobs.indexOf(job);
        job.setStatus(JobStatus.DONE);
        job.setDateDone(new Date());
        job.setUpdatedAt(new Date());
        jobs.set(index, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceIndex, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

    public void deleteJob(UUID workspaceUuid, UUID uuid){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var index = workspaces.indexOf(workspace);
        var jobs = workspace.getJobs();
        jobs.remove(this.getJob(workspaceUuid, uuid));
        workspace.setJobs(jobs);
        workspaces.set(index, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

}
