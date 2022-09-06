package models;

import java.util.Date;
import java.util.List;

public interface IReport {

    String workspaceReport(Workspace workspace, Date initialDate, Date finalDate);

    String workspaceReport(Workspace workspace);

    String jobReport(Job job);

}
