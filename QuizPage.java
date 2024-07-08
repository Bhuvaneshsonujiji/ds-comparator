import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;
    private int currentQuestionIndex;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        currentQuestionIndex = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Question getNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex++);
        }
        return null;
    }

    public void checkAnswer(int userAnswer) {
        Question currentQuestion = questions.get(currentQuestionIndex - 1);
        if (userAnswer == currentQuestion.getCorrectAnswerIndex()) {
            score++;
        }
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }
}

public class QuizPage extends JFrame {
    private Quiz quiz;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton;
    private ButtonGroup optionsGroup;

    public QuizPage() {
        setTitle("Quiz Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        quiz = new Quiz();
        quiz.addQuestion(new Question("What is the capital of France?", new String[]{"Berlin", "Paris", "Rome", "Madrid"}, 1));
        quiz.addQuestion(new Question("Which language is used for Android development?", new String[]{"Swift", "Java", "Python", "Kotlin"}, 3));
        quiz.addQuestion(new Question("Who is ICC No 1 All Rounder?", new String[]{"Jadega", "Rashid khan", "Hardik Pandya", "Stonis"}, 3));
        // Create UI components
        questionLabel = new JLabel();
        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
        }
        nextButton = new JButton("Next");

        // Layout setup
        setLayout(new GridLayout(6, 1));
        add(questionLabel);
        for (JRadioButton option : options) {
            add(option);
        }
        add(nextButton);

        // Next button action
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < options.length; i++) {
                    if (options[i].isSelected()) {
                        quiz.checkAnswer(i);
                        break;
                    }
                }
                if (quiz.hasMoreQuestions()) {
                    displayNextQuestion();
                } else {
                    JOptionPane.showMessageDialog(null, "Your score: " + quiz.getScore() + "/" + quiz.getTotalQuestions(), "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
                    new FeedbackPage().setVisible(true);
                    dispose();
                }
            }
        });

        displayNextQuestion();
    }

    private void displayNextQuestion() {
        Question question = quiz.getNextQuestion();
        if (question != null) {
            questionLabel.setText(question.getQuestionText());
            String[] optionsText = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                options[i].setText(optionsText[i]);
                options[i].setSelected(false);
            }
        }
    }
}
