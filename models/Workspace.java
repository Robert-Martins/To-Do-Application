package models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Workspace extends BaseEntity{

    private List<Job> jobs;

    public Workspace(UUID uuid, String name, String description, Date updatedAt, Date createdAt){
        super(uuid, name, description, updatedAt, createdAt);
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
