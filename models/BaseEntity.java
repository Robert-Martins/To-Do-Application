package models;

import java.util.Date;
import java.util.UUID;

public abstract class BaseEntity {

    private UUID uuid;

    private String name;

    private String description;

    private Date updatedAt;

    private Date createdAt;

    public BaseEntity(String name, String description) {
        this.setUuid(UUID.randomUUID());
        this.setName(name);
        this.setDescription(description);
        this.setUpdatedAt(new Date());
        this.setCreatedAt(new Date());
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String toString(){
        return this.getClass().getName() + this.getName() + " - " + this.getDescription();
    }

}
