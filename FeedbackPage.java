import javax.swing.*;
import java.awt.*;

public class FeedbackPage extends JFrame {
    public FeedbackPage() {
        setTitle("Feedback Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        JLabel feedbackLabel = new JLabel("Thank you for taking the quiz!", SwingConstants.CENTER);
        JButton closeButton = new JButton("Close");

        // Layout setup
        setLayout(new BorderLayout());
        add(feedbackLabel, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

        // Close button action
        closeButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}
