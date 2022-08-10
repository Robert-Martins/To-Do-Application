package models;

import enums.JobStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Job extends BaseEntity {

    private JobStatus status;

    private List<Comment> comments;

    private Date dueDate;

    private Date dateDone;

    public Job(UUID uuid, String name, String description, JobStatus status, Date dueDate, Date updatedAt, Date createdAt){
        super(uuid, name, description, updatedAt, createdAt);
        this.setStatus(JobStatus.TO_DO);
        this.setDueDate(dueDate);
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDateDone() {
        return dateDone;
    }

    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
    }
}
