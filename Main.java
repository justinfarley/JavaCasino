import java.io.*;
import java.util.*;

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
    NOJOB(1), // difficulty: 0
    DOCTOR(2), // difficulty: 50
    SALESASSOCIATE(3), // difficulty: 10
    SCIENTIST(4); // difficulty: 25

    int identifier;

    Job(int i){
        identifier = i;
    }
}

public class Main {

    static final String DATA_PATH = "./JavaCasino/data.txt";
    public static void main(String[] args) throws IOException, InterruptedException {
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
        Job job = Job.NOJOB;
        Bank playerBank = new Bank(0, 0);
        Wallet playerWallet;
        Casino casino = new Casino();
        Random r = new Random();
        FileInputStream data;
        try{
            data = new FileInputStream(DATA_PATH);
        }
        catch(FileNotFoundException e){
            File newFile = new File(DATA_PATH);
            if(newFile.createNewFile())
                data = new FileInputStream(newFile);
            else{
                data = null;
            }
        }
        Data storedData = loadData(data);



        // initialization
        // fill all jobs
        fillScientistQuestions(scientistQuestions);
        // fill currentJobQuestions
        currentJobQuestions.put(Job.DOCTOR, doctorQuestions);
        currentJobQuestions.put(Job.SCIENTIST, scientistQuestions);
        currentJobQuestions.put(Job.SALESASSOCIATE, salesQuestions);
        if (data.available() <= 0) {
            File newData = new File(DATA_PATH);
            PrintWriter writeData = new PrintWriter(newData);

            println("Welcome to the Java Gambling simulator!");
            waitForSeconds(2000);
            print("Enter amount for deposit: ");
            deposit = kb.nextDouble();
            do {
                if (deposit < 100 || deposit > 10000) {
                    println("Please enter a valid deposit: (100-10000)");
                    deposit = kb.nextDouble();
                }
            } while (deposit < 100 || deposit > 10000);
            playerWallet = new Wallet(deposit);
            writeData.println("BANK=" + deposit);
            writeData.println("WALLET=0");
            writeData.println("DAY=1");
            writeData.println("JOB=0");
            writeData.close();
        } else {
        //#region assignData

        day = storedData.day;
        switch (storedData.job) {
            case 1:
                job = Job.NOJOB;
                break;
                case 2:
                job = Job.DOCTOR;
                break;
                case 3:
                job = Job.SALESASSOCIATE;
                break;
                case 4:
                job = Job.SCIENTIST;
                break;
            default:
                break;
        }
        playerWallet = new Wallet(storedData.walletBalance);
        playerBank.setBalance(storedData.bankBalance);

        //#endregion assignData
            println("You have $" + playerBank.getBalance() + " in your bank account, and $"
                    + playerWallet.getBalance() + " in your wallet");
        }
        while (day < 21) {
            waitForSeconds(1000);
            println("Day " + day + ", a " + getDay(day) + " morning . . .");
            for (int i = 0; i < 5; i++) {
                waitForSeconds(1000);
                println("-----");
            }
            if (job == Job.NOJOB) {
                println("Oh no! It looks like you don't have a job! Try picking one now: ");
                waitForSeconds(1000);
                println(
                        "Pick a job you would like to do: \n1. Doctor\n2. Sales Associate\n3. Scientist\nPlease enter a number for the corresponding job:");
                int val = kb.nextInt();

                while (val != 1 && val != 2 && val != 3) {
                    System.out.print("Your number is not valid, enter 1,2, or 3 for the corresponding job: ");
                    val = kb.nextInt();
                }
                switch (val) {
                    case 1:
                        job = Job.DOCTOR;
                        System.out
                                .println("Congratulations on your new job as a Doctor! Your job difficulty is 50/100!");
                        waitForSeconds(1000);
                        playerBank.setSalary(5000);
                        break;
                    case 2:
                        job = Job.SALESASSOCIATE;
                        println(
                                "Congratulations on your new job as a Sales Associate! Your job difficulty is 10/100!");
                        waitForSeconds(1000);
                        playerBank.setSalary(1000);
                        break;
                    case 3:
                        job = Job.SCIENTIST;
                        System.out
                                .println(
                                        "Congratulations on your new job as a Scientist! Your job difficulty is 25/100!");
                        waitForSeconds(1000);
                        playerBank.setSalary(2500);
                        break;
                    default:
                        job = Job.NOJOB;
                        break;
                }
            }

            int rand = r.nextInt(morningSentencePool.length);
            println(morningSentencePool[rand]);
            switch (getDay(day)) {
                case "Monday":
                case "Tuesday":
                case "Wednesday":
                case "Thursday":
                case "Friday":
                    println("Time to go to work!");
                    waitForSeconds(1000);
                    GoToJob(job, currentJobQuestions.get(job), playerBank, playerWallet);
                default:
                    // skip job, go casino or wtv is after
                    println("Since it's a " + getDay(day) + ", you don't need to work! Hooray!");
                    waitForSeconds(2000);
                    println("What would you like to do instead?");
                    waitForSeconds(1000);
                    // stuff to do idk yet
                    // maybe freelance,
            }

            println("It's time for your daily gambling session!");
            // in casino
            casino.inputGame(playerBank, playerWallet);

            // bank trip
            println("You decide to go to the bank.");
            waitForSeconds(1000);
            println("Would you like to: \n1. Withdraw\n2. Deposit\n3. Leave");
            int ans = kb.nextInt();
            while (ans != 1 && ans != 2 && ans != 3) {
                System.out.print("choose a valid option: ");
                ans = kb.nextInt();
            }
            double next;
            println(
                    "Current balances: \nBank: $" + playerBank.getBalance() + "\nWallet: $"
                            + playerWallet.getBalance());
            switch (ans) {
                case 1:
                    waitForSeconds(1000);
                    println("How much would you like to withdraw?");
                    next = kb.nextDouble();
                    // input validation etc then display balances
                    while (next > playerBank.getBalance() || next <= 0) {
                        println(
                                "Enter a valid amount (min 0.01)\nYou have $" + playerBank.getBalance() + ": ");
                        next = kb.nextDouble();
                    }
                    playerBank.withdraw(next, playerWallet, playerBank);
                    break;
                case 2:
                    waitForSeconds(1000);
                    println("How much would you like to deposit?");
                    next = kb.nextDouble();
                    while (next > playerWallet.getBalance() || next <= 0) {
                        System.out
                                .println("Enter a valid amount (min 0.01)\nYou have $" + playerWallet.getBalance()
                                        + ": ");
                        next = kb.nextDouble();
                    }
                    playerBank.deposit(next, playerWallet, playerBank);
                    break;
                case 3:
                    waitForSeconds(1000);
                    println("Ok! Time to go home and go to bed.");
                    break;
            }
            day++;
            playerBank.interestPerDay();
            saveData(playerWallet, playerBank, day, job.identifier);
            println("Progress Saved!");
        }
        kb.close();

    }// end of main

    static void GoToJob(Job job, HashMap<Integer, String[]> questionList, Bank playerBank, Wallet playerWallet) {
        Random r = new Random();
        Scanner scan = new Scanner(System.in);
        int rand = r.nextInt(questionList.size());
        for (int i = 0; i < questionList.get(rand).length - 1; i++) {
            println(questionList.get(rand)[i]);
            waitForSeconds(1000);
        }
        System.out.print("Please enter the corresponding answer: ");
        int guess = scan.nextInt();
        if (checkAnswer(guess, questionList.get(rand)[questionList.get(rand).length - 1])) {
            playerBank.addBalance(playerBank.getSalary());
            println(
                    "Correct! Your salary for today has been paid!\n+$" + playerBank.getSalary() + " dollars!");
            waitForSeconds(2500);
            println("Your new bank balance is: $" + playerBank.getBalance() + " and you have $"
                    + playerWallet.getBalance() + " on hand");
            waitForSeconds(2500);
        } else {
            println("OOPS! Wrong answer.");
            waitForSeconds(2500);
            println("Your bank balance is: $" + playerBank.getBalance() + " and you have $"
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
            println("IException");
        }
    }

    /**
     * SAVES DATA TO A TEXT FILE
     * @param wallet wallet balance to save
     * @param bank bank balance to save
     * @param day day to save
     * @param job job to save
     * @throws IOException dealing with files
     */
    static void saveData(Wallet wallet, Bank bank, int day, int job) throws IOException
    {
        File newData = new File(DATA_PATH);
        PrintWriter writeData = new PrintWriter(newData);

        writeData.println("BANK=" + bank.getBalance());
        writeData.println("WALLET=" + wallet.getBalance());
        writeData.println("DAY=" + day);
        writeData.println("JOB=" + job);
        writeData.close();

    }

    /**
     * LOADS THE DATA FROM THE TEXT FILEthat references the save File
     * @param data the FileInputStream 
     * @return returns a Data object with the retrieved values
     * @throws IOException dealing with files
     */
    static Data loadData(FileInputStream data) throws IOException {

        int day = (int) getDataLine(data, "DAY");
        int job = (int)getDataLine(data, "JOB");
        double walletBal = getDataLine(data, "WALLET");
        double bankBal = getDataLine(data, "BANK");
        
        return new Data(day, walletBal, bankBal, job);

    }

    /**
     * Will get the value of the inputted attribute and return it AS A DOUBLE (even
     * if its an int)
     * 
     * @param data      the file where data is saved
     * @param attribute the attribute of the data to get. Attributes include: "DAY",
     *                  "BANK", WALLET
     */
    static double getDataLine(FileInputStream data, String attribute) throws IOException {
        data = new FileInputStream(DATA_PATH);
        Scanner dataScanner = new Scanner(data);
        double value = 0.0;
        if (!attribute.equals("DAY") && !attribute.equals("BANK") && !attribute.equals("WALLET") && !attribute.equals("JOB")) {
            println("ERROR");
            System.exit(404);
        }
        while (dataScanner.hasNext()) {
            String nextLine = dataScanner.nextLine();
            println(attribute);
            if (nextLine.contains(attribute)) {
                value = Double.parseDouble(nextLine.substring(nextLine.indexOf("=") + 1));
                println(value + " VALUE");
                return value;
            }
        }
        dataScanner.close();
        return value;
    }
    /**
     * the only purpose of this method is to get rid of the annoying errors
     * @param literal string to print
     */
    static void print(String literal){
        System.out.print(literal);
    }
        /**
     * the only purpose of this method is to get rid of the annoying errors
     * @param literal string to print
     */
    static void println(String literal){
        System.out.println(literal);
    }
}
