package controllers;

import enums.JobStatus;
import exceptions.ResourceNotFoundException;
import models.Comment;
import models.Job;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JobController {

    private UserController userController = new UserController();

    private WorkspaceController workspaceController = new WorkspaceController();

    public JobController(){}

    public Job createJob(UUID workspaceUuid, String name, String description, Date dueDate){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var index = workspaces.indexOf(workspace);
        var jobs = workspace.getJobs();
        var job = new Job(name, description, dueDate);
        jobs.add(job);
        workspace.setJobs(jobs);
        workspaces.set(index, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return job;
    }

    public Job getJob(UUID workspaceUuid, UUID uuid){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        return workspace.getJobs().stream()
                .filter(job -> job.getUuid() == uuid)
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Job Not Found"));
    }

    public List<Comment> getJobComments(UUID workspaceUuid, UUID uuid){
        var job = this.getJob(workspaceUuid, uuid);
        return job.getComments();
    }

    public Job updateJob(UUID workspaceUuid, UUID uuid, String name, String description, Date dueDate){
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
        return job;
    }

    public Job jobToToDo(UUID workspaceUuid, UUID uuid){
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
        return job;
    }

    public Job jobToDevelopment(UUID workspaceUuid, UUID uuid){
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
        return job;
    }

    public Job jobToStandby(UUID workspaceUuid, UUID uuid){
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
        return job;
    }

    public Job jobToDone(UUID workspaceUuid, UUID uuid){
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
        return job;
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
