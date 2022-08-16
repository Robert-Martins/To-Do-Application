package models;

import java.util.Date;
import java.util.UUID;

public class Comment {

    private UUID uuid;

    private String comment;

    private Date updatedAt;

    private Date createdAt;

    public Comment(String comment, Date updatedAt, Date createdAt){
        this.setUuid(UUID.randomUUID());
        this.setComment(comment);
        this.setUpdatedAt(updatedAt);
        this.setCreatedAt(createdAt);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
