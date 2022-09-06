package models;

import java.util.Date;

public interface IReport {

    String workspaceReport(Workspace workspace, Date initialDate, Date finalDate);

    String workspaceReport(Workspace workspace);

    String jobReport(Job job);

}
