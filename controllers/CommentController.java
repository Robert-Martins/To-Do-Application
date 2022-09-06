package controllers;

import models.Comment;

import java.util.ArrayList;
import java.util.Date;

public class CommentController {

    private UserController userController = new UserController();

    private WorkspaceController workspaceController = new WorkspaceController();

    private JobController jobController = new JobController();

    public CommentController(){}

    public Comment createComment(int workspaceId, int jobId, String comment){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceId);
        var jobs = workspace.getJobs();
        var job = jobController.getJob(workspaceId, jobId);
        var comments = job.getComments();
        var comm = new Comment(comment);
        if(comments == null)
            comments = new ArrayList<>();
        comments.add(comm);
        job.setComments(comments);
        jobs.set(jobId, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceId, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return comm;
    }

    public Comment getComment(int workspaceId, int jobId, int commentId){
        var job = jobController.getJob(workspaceId, jobId);
        return job.getComments().get(commentId);
    }

    public Comment updateComment(int workspaceId, int jobId, int commentId, String comment){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceId);
        var jobs = workspace.getJobs();
        var job = jobController.getJob(workspaceId, jobId);
        var comments = job.getComments();
        var comm = this.getComment(workspaceId, jobId, commentId);
        comm.setComment(comment);
        comm.setUpdatedAt(new Date());
        comments.set(commentId, comm);
        job.setComments(comments);
        jobs.set(jobId, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceId, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
        return comm;
    }

    public void deleteComment(int workspaceId, int jobId, int commentId){
        var user = userController.getUser();
        var workspaces = user.getWorkspaces();
        var workspace = workspaceController.getWorkspace(workspaceId);
        var jobs = workspace.getJobs();
        var job = jobController.getJob(workspaceId, jobId);
        var comments = job.getComments();
        comments.remove(commentId);
        job.setComments(comments);
        jobs.set(jobId, job);
        workspace.setJobs(jobs);
        workspaces.set(workspaceId, workspace);
        user.setWorkspaces(workspaces);
        userController.saveUser(user);
    }

}
