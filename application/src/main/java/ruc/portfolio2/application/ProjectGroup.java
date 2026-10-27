package ruc.portfolio2.application;

public class ProjectGroup {
    private final int id;
    private final int activityId;
    private final String title;
    private final String supervisor;

    public ProjectGroup(int id, int activityId,
                        String title, String supervisor) {
        this.id = id;
        this.activityId = activityId;
        this.title = title;
        this.supervisor = supervisor;
    }

    public int getId() {
        return id;
    }

    public int getActivityId() {
        return activityId;
    }

    public String getTitle() {
        return title;
    }

    public String getSupervisor() {
        return supervisor;
    }

    @Override
    public String toString() {
        return title + " — " + supervisor;
    }
}