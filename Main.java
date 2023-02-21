import java.util.*;
import java.util.HashMap;

//add days:
//  Certain number of actions per day
//  Job:
//      Pick job at start, each job has distinct set of questions.
//      over the days, if the user gets the questions wrong too many times: lose job, must pick new job
//      if you get a question wrong, you get nothing
//      if a question is answered correctly, money gained is based on the difficulty of the job
//  gamble:
//      Pick a game, play again? etc.
//  when done gambling:
//      
enum Job {
    NoJob, // difficulty: 0
    Doctor, // difficulty: 50
    SalesAssociate, // difficulty: 10
    Scientist, // difficulty: 25
}

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        double deposit;
        int day = 1;
        String[] morningSentencePool = { "What a beautiful morning! Time to put on a smile!", "", "" };
        HashMap<Integer, String[]> scientistQuestion = new HashMap<Integer, String[]>(); // String array is the question
                                                                                         // and the 4 options
        String[] scientistQuestions = {}; // ask chatgpt Give me a list of 20 questions of moderate difficulty that only
                                          // scientists could answer in 4 answer multiple choice format
        String[] salesAssociateQuestions = {};// ask chatgpt Give me a list of 20 questions of moderate difficulty that
                                              // only Sales Associates could answer in 4 answer multiple choice format
        String[] DoctorQuestions = {};// ask chatgpt Give me a list of 20 questions of moderate difficulty that only
                                      // doctors could answer in 4 answer multiple choice format
        Job job;
        Bank playerBank = new Bank(0, 0);
        Casino casino = new Casino();
        Random r = new Random();

        scientistQuestion.put(0, new String[] { "What is this thing?", "option 1", "option 2", "option 3", "option 4",
                /* answer */"2" });

        System.out.print("Enter amount for deposit: ");
        deposit = kb.nextDouble();
        do {
            if (deposit < 100 || deposit > 10000) {
                System.out.println("Please enter a valid deposit: (100-10000)");
                deposit = kb.nextDouble();
            }
        } while (deposit < 100 || deposit > 10000);

        Wallet playerWallet = new Wallet(deposit);
        System.out.println(
                "Pick a job you would like to do: \n1. Doctor\n2. Sales Associate\n3. Scientist\nPlease enter a number for the corresponding job:");
        int val = kb.nextInt();

        while (val != 1 && val != 2 && val != 3) {
            System.out.print("Your number is not valid, enter 1,2, or 3 for the corresponding job: ");
            val = kb.nextInt();
        }
        switch (val) {
            case 1:
                job = Job.Doctor;
                System.out.println("Congratulations on your new job as a Doctor! Your job difficulty is 50/100!");
                playerBank.setSalary(5000);
                break;
            case 2:
                job = Job.SalesAssociate;
                System.out.println(
                        "Congratulations on your new job as a Sales Associate! Your job difficulty is 10/100!");
                playerBank.setSalary(1000);
                break;
            case 3:
                job = Job.Scientist;
                System.out.println("Congratulations on your new job as a Scientist! Your job difficulty is 25/100!");
                playerBank.setSalary(2500);
                break;
            default:
                job = Job.NoJob;
                break;
        }
        System.out.println("Day " + day + ", a " + getDay(day) + " morning . . .");
        for (int i = 0; i < 5; i++) {
            System.out.println("-----");
        }

        int rand = r.nextInt(morningSentencePool.length);
        // if day mod 5 == 0 its friday... etc. maybe make personalized messages for
        // each day
        System.out.println(morningSentencePool[rand]);
        switch (getDay(day)) {
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Sunday":
                GoToJob(job, scientistQuestion, playerBank, playerWallet);
            default:
                // skip job, go casino or wtv is after
        }

        System.out.println("It's time for your daily gambling session!");
        // in casino
        casino.inputGame();

        kb.close();

    }

    static void GoToJob(Job job, HashMap<Integer, String[]> questionList, Bank playerBank, Wallet playerWallet) {
        Random r = new Random();
        Scanner scan = new Scanner(System.in);
        int rand = r.nextInt(questionList.size());
        System.out.println(job + "  " + rand);

        switch (job) {
            case Scientist:
                // print question and options
                for (int i = 0; i < questionList.get(rand).length - 1; i++) {
                    System.out.println(questionList.get(rand)[i]);
                }
                System.out.print("Please enter the corresponding answer: ");
                int guess = scan.nextInt();
                if (checkAnswer(guess, questionList.get(rand)[questionList.get(rand).length - 1])) {
                    playerBank.addBalance(playerBank.getSalary());
                    System.out.println(
                            "Correct! Your salary for today has been paid!\n+$" + playerBank.getSalary() + " dollars!");
                    System.out.println("Your new bank balance is: $" + playerBank.getBalance() + " and you have $"
                            + playerWallet.getBalance() + " on hand");
                } else {
                    System.out.println("OOPS! Wrong answer.");
                    System.out.println("Your bank balance is: $" + playerBank.getBalance() + " and you have $"
                            + playerWallet.getBalance() + " on hand");
                }
                break;
            case SalesAssociate:
                break;
            case Doctor:
                break;
            default:
                // if no job.....
        }
    }

    static String getDay(int day) {
        if (day % 7 == 0) {
            return "Saturday";
        } else if (day % 6 == 0) {
            return "Friday";
        } else if (day % 5 == 0) {
            return "Thursday";
        } else if (day % 4 == 0) {
            return "Wednesday";
        } else if (day % 3 == 0) {
            return "Tuesday";
        } else if (day % 2 == 0) {
            return "Monday";
        } else if (day % 1 == 0) {
            return "Sunday";
        } else {
            return "ERROR";
        }

    }

    static boolean checkAnswer(int guess, String answer) {
        int ans = Integer.parseInt(answer);
        if (guess == ans) {
            return true;
        } else {
            return false;
        }
    }
}
