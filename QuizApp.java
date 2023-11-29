import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    static int currentQuestionIndex = 0;
    static int userScore = 0;
    static Question[] questions;
    static Timer timer;

    public static void main(String[] args) {
        initializeQuestions();
        startQuiz();
    }

    static void initializeQuestions() {
        questions = new Question[3];
        questions[0] = new Question("Where was the first example of paper money used?",
                new String[] { "Greece", "Turkey", "China", "Russia" }, 2);
        questions[1] = new Question("Who is generally considered the inventor of the motor car?",
                new String[] { "Henry Ford", "Karl Benz", "Henry M. Leland", "Gottlieb Daimler" }, 1);
        questions[2] = new Question("What color is an airplane's famous black box?",
                new String[] { "Orange", "Red", "Black", "White" }, 0);
    }

    static void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz!");
        System.out.println("--------------------");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                displayNextQuestion();
            }
        }, 20000);

        displayNextQuestion();
    }

    static void displayNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question currentQuestion = questions[currentQuestionIndex];
            System.out.println("\n" + currentQuestion.getQuestionText());

            for (int i = 0; i < currentQuestion.getOptions().length; i++) {
                System.out.println((i + 1) + ". " + currentQuestion.getOptions()[i]);
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Select your answer (1-" + currentQuestion.getOptions().length + "): ");
            int userAnswer = scanner.nextInt() - 1;

            if (userAnswer == currentQuestion.getCorrectAnswer()) {
                userScore++;
            }

            currentQuestionIndex++;
            timer.cancel();
            timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    displayNextQuestion();
                }
            }, 20000);

            displayNextQuestion();
        } else {
            endQuiz();
        }
    }

    static void endQuiz() {
        System.out.println("\nQuiz Finished!");
        System.out.println("Your score: " + userScore + "/" + questions.length);
        System.out.println("Thank you for playing!");
    }
}

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}