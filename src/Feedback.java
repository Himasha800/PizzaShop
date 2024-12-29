








import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Feedback {
    private String pizzaName;
    private String comments;
    private int rating; 
    private static List<Feedback> feedbackList = new ArrayList<>(); 

    public Feedback(String pizzaName, String comments, int rating) {
        this.pizzaName = pizzaName;
        this.comments = comments;
        this.rating = rating;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getComments() {
        return comments;
    }

    public int getRating() {
        return rating;
    }

    public void displayFeedback() {
        System.out.println("\n--- Customer Feedback ---");
        System.out.println("Pizza: " + pizzaName);
        System.out.println("Rating: " + rating + " / 5");
        System.out.println("Comments: " + comments);
    }

    public void askToSaveFeedback() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWould you like to save this feedback for future reference? (yes/no): ");
        String saveChoice = sc.nextLine();

        if ("yes".equalsIgnoreCase(saveChoice)) {
            UserProfile.feedbackList.add(this); 
            CommandInvoker invoker = new CommandInvoker();
            SaveFeedbackCommand saveCommand = new SaveFeedbackCommand(this, feedbackList);
            invoker.setCommand(saveCommand);
            invoker.executeCommand();
        } else {
            System.out.println("Feedback not saved.");
        }
    }


    

}
