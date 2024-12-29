import java.util.List;

class SaveFeedbackCommand implements Command {
    private Feedback feedback;
    private List<Feedback> feedbackList;

    public SaveFeedbackCommand(Feedback feedback, List<Feedback> feedbackList) {
        this.feedback = feedback;
        this.feedbackList = feedbackList;
    }

    @Override
    public void execute() {
        feedbackList.add(feedback);
        System.out.println("Feedback saved successfully!");
    }

    @Override
    public void undo() {
        feedbackList.remove(feedback);
        System.out.println("Feedback removal undone.");
    }
}
