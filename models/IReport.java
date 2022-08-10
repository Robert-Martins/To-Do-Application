package models;

import java.util.List;

public interface IReport {

    String userReport(List<Workspace> workspaces);

    String workspaceReport(Workspace workspace);

    String jobReport(Job job);

}
