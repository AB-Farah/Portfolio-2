package ruc.portfolio2.application;

class Controller {
    private Model model;
    private View view;

    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    void loadLists() {
        view.setStudents(model.getStudents());
        view.setActivities(model.getActivites());
    }
    void signUp(String student, String activity) {
        if (student == null || activity == null) {
            view.setFeedback("Choose a student and an activity");
        return;
        }
        try {
            model.SignUp(student, activity);
            view.setFeedback("Signed Up.");
        } catch (RuntimeException e) {
            view.setFeedback("Already signed up.");
        }
    }
    void loadGroups(String activity) {
        view.setGroups(model.getGroups(activity));
    }

    void joinGroup(String student, String activity, String title){
        if (student == null || activity == null || title == null) {
            view.setFeedback("Select a student, activity and group");
            return;
        }
        try {
            model.JoinGroup(student, activity, title);
            view.setFeedback("Joined group");
        } catch (RuntimeException e){
            view.setFeedback("Could not join group");
        }
    }
    void createGroup(String student, String activity, String title, String supervisor){
        if (student == null || activity == null || title.isEmpty() || supervisor.isEmpty()){
            view.setFeedback("Fill in the boxfields");
            return;
        }
        try {
            model.createGroup(student, activity, title, supervisor);
            view.setFeedback("Project Group created");
            loadGroups(activity);
        } catch (RuntimeException e){
            view.setFeedback("Could not create Project Group");
        }
    }
}