package models;

import java.util.Date;
import java.util.List;

public interface IReport {

    void userReport(List<Workspace> workspaces);

    void workspaceReport(Workspace workspace, Date initialDate, Date finalDate);

    void workspaceReport(Workspace workspace);

    void jobReport(Job job);

}
