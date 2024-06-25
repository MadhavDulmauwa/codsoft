import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    static class Question {
        String question;
        String[] options;
        int correctAnswer;

        Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    private static Question[] questions = {
        new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3),
        new Question("Who wrote 'Hamlet'?", new String[]{"1. Charles Dickens", "2. J.K. Rowling", "3. William Shakespeare", "4. Mark Twain"}, 3),
        new Question("What is the smallest planet in our solar system?", new String[]{"1. Earth", "2. Mars", "3. Mercury", "4. Venus"}, 3)
    };

    private static int score = 0;
    private static int questionIndex = 0;
    private static boolean answerSubmitted = false;
    private static boolean timeUp = false;
    private static Timer timer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (questionIndex = 0; questionIndex < questions.length; questionIndex++) {
            displayQuestion(scanner);
        }

        System.out.println("Quiz Finished!");
        System.out.println("Your final score is: " + score + "/" + questions.length);
    }

    private static void displayQuestion(Scanner scanner) {
        Question currentQuestion = questions[questionIndex];
        System.out.println(currentQuestion.question);
        for (String option : currentQuestion.options) {
            System.out.println(option);
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answerSubmitted) {
                    timeUp = true;
                    System.out.println("\nTime's up! Moving to next question.");
                    answerSubmitted = true;
                }
            }
        }, 10000); // 10 seconds timer

        while (!answerSubmitted && !timeUp) {
            System.out.print("Enter your answer (1-4): ");
            if (scanner.hasNextInt()) {
                int answer = scanner.nextInt();
                if (answer >= 1 && answer <= 4) {
                    answerSubmitted = true;
                    timer.cancel();
                    if (answer == currentQuestion.correctAnswer) {
                        score++;
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Incorrect! The correct answer was " + currentQuestion.correctAnswer);
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Clear the invalid input
            }
        }

        if (timeUp) {
            timeUp = false;
        }

        answerSubmitted = false;
        System.out.println();
    }
}
