package controllers;

import enums.JobStatus;
import exceptions.ResourceNotFoundException;
import models.Comment;
import models.Job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobController {

    private final UserController userController = new UserController();

    private final WorkspaceController workspaceController = new WorkspaceController();

    public JobController(){}

    public Job createJob(int workspaceIndex, String name, String description, Date dueDate){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceIndex);
        var jobs = workspace.getJobs();
        var job = new Job(name, description, dueDate);
        if(jobs == null)
            jobs = new ArrayList<>();
        jobs.add(job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceIndex, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return job;
    }

    public Job getJob(int workspaceIndex, int index){
        var workspace = workspaceController.getWorkspace(workspaceIndex);
        if(workspace.getJobs().size() <= index)
            throw new ResourceNotFoundException("Job não encontrado");
        return workspace.getJobs().get(index);
    }

    public List<Comment> getJobComments(int workspaceIndex, int index){
        var job = this.getJob(workspaceIndex, index);
        return job.getComments();
    }

    public Job updateJob(int workspaceId, int id, String name, String description, Date dueDate){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        if(workspaces.size() <= workspaceId)
            throw new ResourceNotFoundException("Workspace não encontrado");
        var workspace = workspaceController.getWorkspace(workspaceId);
        var jobs = workspace.getJobs();
        if(jobs.size() <= id)
            throw new ResourceNotFoundException("Job não encontrado");
        var job = this.getJob(workspaceId, id);
        job.setName(name);
        job.setDescription(description);
        job.setDueDate(dueDate);
        job.setUpdatedAt(new Date());
        jobs.set(id, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceId, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return job;
    }

    public Job jobToToDo(int workspaceId, int id){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        if(workspaces.size() <= workspaceId)
            throw new ResourceNotFoundException("Workspace não encontrado");
        var workspace = workspaceController.getWorkspace(workspaceId);
        var jobs = workspace.getJobs();
        if(jobs.size() <= id)
            throw new ResourceNotFoundException("Job não encontrado");
        var job = this.getJob(workspaceId, id);
        job.setStatus(JobStatus.TO_DO);
        job.setUpdatedAt(new Date());
        jobs.set(id, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceId, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return job;
    }

    public Job jobToDevelopment(int workspaceId, int id){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        if(workspaces.size() <= workspaceId)
            throw new ResourceNotFoundException("Workspace não encontrado");
        var workspace = workspaceController.getWorkspace(workspaceId);
        var jobs = workspace.getJobs();
        if(jobs.size() <= id)
            throw new ResourceNotFoundException("Job não encontrado");
        var job = this.getJob(workspaceId, id);
        job.setStatus(JobStatus.DEVELOPMENT);
        job.setUpdatedAt(new Date());
        jobs.set(id, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceId, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return job;
    }

    public Job jobToStandby(int workspaceId, int id){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        if(workspaces.size() <= workspaceId)
            throw new ResourceNotFoundException("Workspace não encontrado");
        var workspace = workspaceController.getWorkspace(workspaceId);
        var jobs = workspace.getJobs();
        if(jobs.size() <= id)
            throw new ResourceNotFoundException("Job não encontrado");
        var job = this.getJob(workspaceId, id);
        job.setStatus(JobStatus.STAND_BY);
        job.setUpdatedAt(new Date());
        jobs.set(id, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceId, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return job;
    }

    public Job jobToDone(int workspaceId, int id){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        if(workspaces.size() <= workspaceId)
            throw new ResourceNotFoundException("Workspace não encontrado");
        var workspace = workspaceController.getWorkspace(workspaceId);
        var jobs = workspace.getJobs();
        if(jobs.size() <= id)
            throw new ResourceNotFoundException("Job não encontrado");
        var job = this.getJob(workspaceId, id);
        job.setStatus(JobStatus.DONE);
        job.setDateDone(new Date());
        job.setUpdatedAt(new Date());
        jobs.set(id, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceId, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return job;
    }

    public void deleteJob(int workspaceId, int id){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        if(workspaces.size() <= workspaceId)
            throw new ResourceNotFoundException("Workspace não encontrado");
        var workspace = workspaceController.getWorkspace(workspaceId);
        var jobs = workspace.getJobs();
        if(jobs.size() <= id)
            throw new ResourceNotFoundException("Job não encontrado");
        jobs.remove(id);
        workspace.setJobs(jobs);
        workspaces.set(workspaceId, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

}
