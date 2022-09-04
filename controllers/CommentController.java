package controllers;

import exceptions.ResourceNotFoundException;
import models.Comment;

import java.util.Date;
import java.util.UUID;

public class CommentController {

    private UserController userController = new UserController();

    private WorkspaceController workspaceController = new WorkspaceController();

    private JobController jobController = new JobController();

    public CommentController(){}

    public Comment createComment(UUID workspaceUuid, UUID jobUuid, String comment){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var jobs = workspace.getJobs();
        var job = jobController.getJob(workspaceUuid, jobUuid);
        var comments = job.getComments();
        var workspaceIndex = workspaces.indexOf(workspace);
        var jobIndex = jobs.indexOf(job);
        var comm = new Comment(comment);
        comments.add(comm);
        job.setComments(comments);
        jobs.set(jobIndex, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceIndex, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return comm;
    }

    public Comment getComment(UUID workspaceUuid, UUID jobUuid, UUID uuid){
        var job = jobController.getJob(workspaceUuid, jobUuid);
        return job.getComments().stream()
                .filter(comment -> comment.getUuid() == uuid)
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Comment Not Found"));
    }

    public Comment updateComment(UUID workspaceUuid, UUID jobUuid, UUID uuid, String comment){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var jobs = workspace.getJobs();
        var job = jobController.getJob(workspaceUuid, jobUuid);
        var comments = job.getComments();
        var comm = this.getComment(workspaceUuid, jobUuid, uuid);
        var workspaceIndex = workspaces.indexOf(workspace);
        var jobIndex = jobs.indexOf(job);
        var index = comments.indexOf(comment);
        comm.setComment(comment);
        comm.setUpdatedAt(new Date());
        comments.set(index, comm);
        job.setComments(comments);
        jobs.set(jobIndex, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceIndex, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return comm;
    }

    public void deleteComment(UUID workspaceUuid, UUID jobUuid, UUID uuid){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var jobs = workspace.getJobs();
        var job = jobController.getJob(workspaceUuid, jobUuid);
        var comments = job.getComments();
        var comm = this.getComment(workspaceUuid, jobUuid, uuid);
        var workspaceIndex = workspaces.indexOf(workspace);
        var jobIndex = jobs.indexOf(job);
        comments.remove(comm);
        job.setComments(comments);
        jobs.set(jobIndex, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceIndex, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

}
