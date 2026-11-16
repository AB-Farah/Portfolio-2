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
            view.setFeedback("select a student and an activity");
        return;
        }
        try {
            model.SignUp(student, activity);
            view.setFeedback("Signup saved");
        } catch (RuntimeException e) {
            view.setFeedback("Already signed up.");
        }
    }

}