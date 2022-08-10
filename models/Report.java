package models;

import java.util.List;

public class Report implements IReport{

    @Override
    public String userReport(List<Workspace> workspaces){
        return "ooeoe";
    }

    @Override
    public String workspaceReport(Workspace workspace){
        return "oeooe";
    }

    @Override
    public String jobReport(Job job){
        return "oiio";
    }

}
