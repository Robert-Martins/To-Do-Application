package controllers;

import exceptions.ResourceNotFoundException;
import models.Comment;

import java.util.Date;
import java.util.UUID;

public class CommentController {

    final UserController userController;

    final WorkspaceController workspaceController;

    final JobController jobController;

    public CommentController(UserController userController, WorkspaceController workspaceController, JobController jobController){
        this.userController = userController;
        this.workspaceController = workspaceController;
        this.jobController = jobController;
    }

    public void createComment(UUID workspaceUuid, UUID jobUuid, String comment){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceUuid);
        var jobs = workspace.getJobs();
        var job = jobController.getJob(workspaceUuid, jobUuid);
        var comments = job.getComments();
        var workspaceIndex = workspaces.indexOf(workspace);
        var jobIndex = jobs.indexOf(job);
        comments.add(new Comment(comment));
        job.setComments(comments);
        jobs.set(jobIndex, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceIndex, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

    public Comment getComment(UUID workspaceUuid, UUID jobUuid, UUID uuid){
        var job = jobController.getJob(workspaceUuid, jobUuid);
        return job.getComments().stream()
                .filter(comment -> comment.getUuid() == uuid)
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Comment Not Found"));
    }

    public void updateComment(UUID workspaceUuid, UUID jobUuid, UUID uuid, String comment){
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
