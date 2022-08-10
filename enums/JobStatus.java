package enums;

public enum JobStatus {

    TO_DO("To-Do"),
    DEVELOPMENT("Development"),
    DONE("Done"),
    STAND_BY("Stand-by")
    ;
    public final String name;

    JobStatus(final String name){
        this.name = name;
    }

}
