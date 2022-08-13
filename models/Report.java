package models;

import enums.JobStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Report implements IReport{

    private int workspacesNumber;

    private int toDoJobs;

    private int developmentJobs;

    private int doneJobs;

    private int standByJobs;

    private int expiredDateJobs;

    private int createdJobs;

    @Override
    public void userReport(List<Workspace> workspaces){
        clearAttributes();
        workspaces.forEach(workspace -> {
                    this.setWorkspacesNumber(this.getWorkspacesNumber()+1);
                    workspaceReport(workspace);
                });
    }

    @Override
    public void workspaceReport(Workspace workspace, Date initialDate, Date finalDate){
        clearAttributes();
        workspace.getJobs().stream()
                .filter(job ->
                        (initialDate.after(job.getCreatedAt()) && finalDate.before(job.getCreatedAt()))
                        || (initialDate.after(job.getUpdatedAt()) && finalDate.before(job.getUpdatedAt()))
                        || (initialDate.after(job.getDueDate()) && finalDate.before(job.getDueDate()))
                        || (initialDate.after(job.getDateDone()) && finalDate.before(job.getDateDone()))
                )
                .forEach(job -> {
                    if(job.getStatus() == JobStatus.TO_DO)
                        this.setToDoJobs(this.getToDoJobs()+1);
                    if(job.getStatus() == JobStatus.DEVELOPMENT)
                        this.setDevelopmentJobs(this.getDevelopmentJobs()+1);
                    if(job.getStatus() == JobStatus.DONE)
                        this.setDoneJobs(this.getDoneJobs()+1);
                    if(job.getStatus() == JobStatus.STAND_BY)
                        this.setStandByJobs(this.getStandByJobs()+1);
                    if(new Date().after(job.getDueDate()))
                        this.setExpiredDateJobs(this.getExpiredDateJobs()+1);
                    this.setCreatedJobs(this.getCreatedJobs()+1);
                });
        System.out.println(
                "====="+workspace.getName()+"=====\n"
                        +"----------\n"
                        +" - To Do Jobs: "+this.getToDoJobs()+"\n"
                        +" - Development Jobs: "+this.getToDoJobs()+"\n"
                        +" - Done Jobs: "+this.getToDoJobs()+"\n"
                        +" - Stand-By Jobs: "+this.getToDoJobs()+"\n"
                        +" - Expired Date Jobs: "+this.getToDoJobs()+"\n"
                        +" - Total Jobs: "+this.getToDoJobs()+"\n"
        );
    }

    @Override
    public void workspaceReport(Workspace workspace){
        clearAttributes();
        workspace.getJobs().forEach(job -> {
                    if(job.getStatus() == JobStatus.TO_DO)
                      this.setToDoJobs(this.getToDoJobs()+1);
                    if(job.getStatus() == JobStatus.DEVELOPMENT)
                        this.setDevelopmentJobs(this.getDevelopmentJobs()+1);
                    if(job.getStatus() == JobStatus.DONE)
                        this.setDoneJobs(this.getDoneJobs()+1);
                    if(job.getStatus() == JobStatus.STAND_BY)
                        this.setStandByJobs(this.getStandByJobs()+1);
                    if(new Date().after(job.getDueDate()))
                        this.setExpiredDateJobs(this.getExpiredDateJobs()+1);
                    this.setCreatedJobs(this.getCreatedJobs()+1);
                });
        System.out.println(
                        "====="+workspace.getName()+"=====\n"
                        +"----------\n"
                        +" - To Do Jobs: "+this.getToDoJobs()+"\n"
                        +" - Development Jobs: "+this.getToDoJobs()+"\n"
                        +" - Done Jobs: "+this.getToDoJobs()+"\n"
                        +" - Stand-By Jobs: "+this.getToDoJobs()+"\n"
                        +" - Expired Date Jobs: "+this.getToDoJobs()+"\n"
                        +" - Total Jobs: "+this.getToDoJobs()+"\n"
        );
    }

    @Override
    public void jobReport(Job job){
        var report = "====="+job.getName()+"=====\n"
                +"- Description: "+job.getDescription()+"\n"
                +"- Current job status: "+job.getStatus()+"\n"
                +"- Created At: "+job.getCreatedAt()+"\n"
                +"- Updated At: "+job.getUpdatedAt()+"\n"
                +"- Due Date: "+job.getDueDate()+"\n";
        if(new Date().after(job.getDueDate()))
            report += "*****JOB EXPIRED*****\n";
        if(job.getStatus() == JobStatus.DONE)
            report += "- Date of conclusion: "+job.getDateDone()+"\n";
        System.out.println(

        );
    }

    private void clearAttributes(){
        this.setWorkspacesNumber(0);
        this.setToDoJobs(0);
        this.setDevelopmentJobs(0);
        this.setDoneJobs(0);
        this.setStandByJobs(0);
        this.setExpiredDateJobs(0);
        this.setCreatedJobs(0);
    }

    public int getWorkspacesNumber() {
        return workspacesNumber;
    }

    public void setWorkspacesNumber(int workspacesNumber) {
        this.workspacesNumber = workspacesNumber;
    }

    public int getToDoJobs() {
        return toDoJobs;
    }

    public void setToDoJobs(int toDoJobs) {
        this.toDoJobs = toDoJobs;
    }

    public int getDevelopmentJobs() {
        return developmentJobs;
    }

    public void setDevelopmentJobs(int developmentJobs) {
        this.developmentJobs = developmentJobs;
    }

    public int getDoneJobs() {
        return doneJobs;
    }

    public void setDoneJobs(int doneJobs) {
        this.doneJobs = doneJobs;
    }

    public int getStandByJobs() {
        return standByJobs;
    }

    public void setStandByJobs(int standByJobs) {
        this.standByJobs = standByJobs;
    }

    public int getExpiredDateJobs() {
        return expiredDateJobs;
    }

    public void setExpiredDateJobs(int expiredDateJobs) {
        this.expiredDateJobs = expiredDateJobs;
    }

    public int getCreatedJobs() {
        return createdJobs;
    }

    public void setCreatedJobs(int createdJobs) {
        this.createdJobs = createdJobs;
    }
}
