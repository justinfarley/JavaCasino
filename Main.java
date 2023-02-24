import java.util.*;
import java.util.HashMap;
import java.lang.Object.*;

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
    public static void main(String[] args) throws InterruptedException {
        Scanner kb = new Scanner(System.in);
        double deposit;
        int day = 1;
        String[] morningSentencePool = { "What a beautiful morning! Time to put on a smile!", "", "" };
        HashMap<Integer, String[]> scientistQuestions = new HashMap<Integer, String[]>(); // String array is the
        HashMap<Integer, String[]> salesQuestions = new HashMap<Integer, String[]>(); // String array is the question
        HashMap<Integer, String[]> doctorQuestions = new HashMap<Integer, String[]>(); // String array is the question
        HashMap<Job, HashMap<Integer, String[]>> currentJobQuestions = new HashMap<Job, HashMap<Integer, String[]>>(); // and
                                                                                                                       // the
                                                                                                                       // 4
                                                                                                                       // options
        // ask chatgpt Give me a list of 20 questions of moderate difficulty that only
        // scientists could answer in 4 answer multiple choice format
        // ask chatgpt Give me a list of 20 questions of moderate difficulty that
        // only Sales Associates could answer in 4 answer multiple choice format
        // ask chatgpt Give me a list of 20 questions of moderate difficulty that only
        // doctors could answer in 4 answer multiple choice format
        Job job;
        Bank playerBank = new Bank(0, 0);
        Casino casino = new Casino();
        Random r = new Random();
        // initialization
        // fill all jobs
        fillScientistQuestions(scientistQuestions);
        // fill currentJobQuestions
        currentJobQuestions.put(Job.Doctor, doctorQuestions);
        currentJobQuestions.put(Job.Scientist, scientistQuestions);
        currentJobQuestions.put(Job.SalesAssociate, salesQuestions);
        System.out.println("Welcome to the Java Gambling simulator!");
        waitForSeconds(2000);
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
        waitForSeconds(1000);
        switch (val) {
            case 1:
                job = Job.Doctor;
                System.out.println("Congratulations on your new job as a Doctor! Your job difficulty is 50/100!");
                waitForSeconds(1000);
                playerBank.setSalary(5000);
                break;
            case 2:
                job = Job.SalesAssociate;
                System.out.println(
                        "Congratulations on your new job as a Sales Associate! Your job difficulty is 10/100!");
                waitForSeconds(1000);
                playerBank.setSalary(1000);
                break;
            case 3:
                job = Job.Scientist;
                System.out.println("Congratulations on your new job as a Scientist! Your job difficulty is 25/100!");
                waitForSeconds(1000);
                playerBank.setSalary(2500);
                break;
            default:
                job = Job.NoJob;
                break;
        }
        waitForSeconds(1000);
        System.out.println("Day " + day + ", a " + getDay(day) + " morning . . .");
        for (int i = 0; i < 5; i++) {
            waitForSeconds(1000);
            System.out.println("-----");
        }
        int rand = r.nextInt(morningSentencePool.length);
        System.out.println(morningSentencePool[rand]);
        switch (getDay(day)) {
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
                System.out.println("Time to go to work!");
                waitForSeconds(1000);
                GoToJob(job, currentJobQuestions.get(job), playerBank, playerWallet);
            default:
                // skip job, go casino or wtv is after
                System.out.println("Since it's a " + getDay(day) + ", you don't need to work! Hooray!");
                waitForSeconds(2000);
                System.out.println("What would you like to do instead?");
                waitForSeconds(1000);
                // stuff to do idk yet
        }

        System.out.println("It's time for your daily gambling session!");
        // in casino
        casino.inputGame(playerBank, playerWallet);

        // bank trip
        System.out.println("You decide to go to the bank.");
        waitForSeconds(1000);
        System.out.println("Would you like to: \n1. Withdraw\n2. Deposit\n3. Leave");
        int ans = kb.nextInt();
        while (ans != 1 && ans != 2 && ans != 3) {
            System.out.print("choose a valid option: ");
            ans = kb.nextInt();
        }
        switch (ans) {
            case 1:
                waitForSeconds(1000);
                System.out.println("How much would you like to withdraw?");
                double next = kb.nextDouble();
                //input validation etc then display balances
                break;
            case 2:
                break;
            case 3:
                break;
        }
        kb.close();

    }// end of main

    static void GoToJob(Job job, HashMap<Integer, String[]> questionList, Bank playerBank, Wallet playerWallet) {
        Random r = new Random();
        Scanner scan = new Scanner(System.in);
        int rand = r.nextInt(questionList.size());
        for (int i = 0; i < questionList.get(rand).length - 1; i++) {
            System.out.println(questionList.get(rand)[i]);
            waitForSeconds(1000);
        }
        System.out.print("Please enter the corresponding answer: ");
        int guess = scan.nextInt();
        if (checkAnswer(guess, questionList.get(rand)[questionList.get(rand).length - 1])) {
            playerBank.addBalance(playerBank.getSalary());
            System.out.println(
                    "Correct! Your salary for today has been paid!\n+$" + playerBank.getSalary() + " dollars!");
            waitForSeconds(2500);
            System.out.println("Your new bank balance is: $" + playerBank.getBalance() + " and you have $"
                    + playerWallet.getBalance() + " on hand");
            waitForSeconds(2500);
        } else {
            System.out.println("OOPS! Wrong answer.");
            waitForSeconds(2500);
            System.out.println("Your bank balance is: $" + playerBank.getBalance() + " and you have $"
                    + playerWallet.getBalance() + " on hand");
            waitForSeconds(2500);
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

    static void fillScientistQuestions(HashMap<Integer, String[]> scientistQuestions) {
        scientistQuestions.put(0,
                new String[] {
                        "What is the term used to describe the process of hydrogen atoms fusing to form helium atoms?",
                        "1. Fission", "2. Fusion", "3. Transmutation", "4. Oxidation",
                        /* answer */"2" });

        scientistQuestions.put(1,
                new String[] { "What is the chemical formula for sulfuric acid?",
                        "1. H3SO3", "2. H2SO3", "3. H2SO4", "4. H3SO4", "3" });

        scientistQuestions.put(2,
                new String[] { "What is the atomic number of carbon?",
                        "1. 6", "2. 8", "3. 10", "4. 12", "1" });

        scientistQuestions.put(3,
                new String[] { "What is the pressure at the center of the Earth?",
                        "1. Approximately 1 atmosphere", "2. Approximately 10 atmospheres",
                        "3. Approximately 100 atmospheres", "4. Approximately 1000 atmospheres", "3" });
        scientistQuestions.put(4,
                new String[] { "What is the main component of natural gas?",
                        "1. Carbon dioxide", "2. Methane", "3. Nitrogen", "4. Oxygen", "2" });
        scientistQuestions.put(5,
                new String[] { "What is the speed of light in meters per second?",
                        "1. 299,792 km/s", "2. 299,792 m/s", "3. 300,000 km/s", "4. 300,000 m/s", "1" });
        scientistQuestions.put(6,
                new String[] { "What is the smallest known particle?",
                        "1. Proton", "2. Neutron", "3. Electron", "4. Quark", "4" });
        scientistQuestions.put(7,
                new String[] { "What is the most common element in the universe?",
                        "1. Hydrogen", "2. Oxygen", "3. Helium", "4. Carbon", "1" });
        scientistQuestions.put(8,
                new String[] { "What type of radiation is used to treat cancer patients?",
                        "1. Gamma", "2. X-Ray", "3. Ultraviolet", "4. Infrared", "1" });
        scientistQuestions.put(9,
                new String[] { "What is the boiling point of nitrogen?",
                        "1. -196°C", "2. -200°C", "3. -196°F", "4. -200°F", "1" });
        scientistQuestions.put(10,
                new String[] { "What is the unit of measure used to describe the energy of particles?",
                        "1. Joule", "2. Hertz", "3. Newton", "4. Electronvolt", "4" });
        scientistQuestions.put(11,
                new String[] { "What is the name of the theory that explains the behavior of subatomic particles?",
                        "1. General Relativity", "2. Quantum Mechanics", "3. Chaos Theory", "4. Atomic Theory", "2" });

    }

    static void waitForSeconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("IException");
        }
    }

}
