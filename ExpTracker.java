import java.util.Scanner;

class ExpTracker {
    static Scanner scan = new Scanner(System.in);
    static String[][] expInfo = new String[0][4];
    static int expCount = 0;

    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Linux")) {
                System.out.print("\033\143");
            } else if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (final Exception e) {
            // Handle the exception
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        homePg();
    }

    public static void homePg() {
        clearConsole();
        System.out.println("_________________________EXPENSE TRACKER_________________________");
        System.out.println();
        System.out.println("   1. Add Expense                   2. Update Expense\n");
        System.out.println("   3. Delete Expense                4. View All Expenses\n");
        System.out.println("   5. All Expenses Summary          6. Monthly Expenses Summary\n");
        System.out.print("        Select Option :");
        int opt1 = scan.nextInt();

        switch (opt1) {
            case 1 -> addExp();
            case 2 -> updateExp();
            case 3 -> deleteExp();
            case 4 -> viewAllExp();
            case 5 -> allExpSummary();
            case 6 -> monthlyExpSummary();
        }
    }

    public static void copyArray1() {
        String temp[][] = new String[expCount + 1][4];
        for (int i = 0; i < expCount; i++) {
            temp[i] = expInfo[i];
        }
        expInfo = temp;
    }

    public static void addExp() {
        clearConsole();
        while (true) {
            copyArray1();
            System.out.println("                     ADD EXPENSE");
            System.out.println("-------------------------------------------------------\n");
            System.out.print("    1. Expense ID          : ");
            String expid = scan.next();
            System.out.print("    2. Amount              : ");
            String amnt = scan.next();
            System.out.print("    3. Date                : ");
            String date = scan.next();
            System.out.print("    4. Expense Description : ");
            String desc = scan.next();

            expInfo[expCount][0] = expid;
            expInfo[expCount][1] = amnt;
            expInfo[expCount][2] = date;
            expInfo[expCount][3] = desc;
            expCount++;

            System.out.println();
            System.out.print("    Do you wanna add another Expense? (y/n): ");
            String opt2 = scan.next();

            if (opt2.equalsIgnoreCase("y")) {
                clearConsole();
                addExp();
            } else if (opt2.equalsIgnoreCase("n")) {
                clearConsole();
                homePg();
            } else {
                System.out.println("    Invalid input! Try again.");
                opt2 = scan.next();
            }
        }
    }

    public static void updateExp() {
        clearConsole();
        while (true) {
            System.out.println("                    UPDATE EXPENSE");
            System.out.println("-------------------------------------------------------\n");

            if (expCount == 0) {
                System.out.println("No expenses available to update.");
                back();
            }

            System.out.print("    1. Expense ID              : ");
            String expid = scan.next();

            int expIndex = -1;

            for (int i = 0; i < expCount; i++) {
                if (expid.equals(expInfo[i][0])) {
                    expIndex = i;
                    break;
                }
            }

            if (expIndex == -1) {
                System.out.println("            Invalid Expense Id! Please enter the corret one.");
                // expid = scan.next();
                continue;
            }

            System.out.print("    1. New Expense Amount      : ");
            expInfo[expIndex][1] = scan.next();
            System.out.print("    2. New Date                : ");
            expInfo[expIndex][2] = scan.next();
            System.out.print("    3. New Expense Description : ");
            expInfo[expIndex][3] = scan.next();
            System.out.println();

            System.out.print("    Do you wanna Update another expense? (y/n): ");
            String opt3 = scan.next();

            if (opt3.equalsIgnoreCase("y")) {
                clearConsole();
                updateExp();
            } else if (opt3.equalsIgnoreCase("n")) {
                clearConsole();
                homePg();
            } else {
                System.out.println("Invalid input! Try again.");
                opt3 = scan.next();
            }
            break;
        }
    }

    public static void deleteExp() {
        clearConsole();
        while (true) {
            System.out.println("                    DELETE EXPENSE");
            System.out.println("-------------------------------------------------------\n");

            if (expCount == 0) {
                System.out.println("No expenses available to delete.");
                back();
            }

            System.out.print("    1. Expense ID              : ");
            String expid = scan.next();

            int expIndex = -1;

            for (int i = 0; i < expCount; i++) {
                if (expid.equals(expInfo[i][0])) {
                    expIndex = i;
                    break;
                }
            }

            if (expIndex == -1) {
                System.out.println("            Invalid Expense Id! Please enter the corret one.");
                // expid = scan.next();
                continue;
            } else {
                for (int i = expIndex; i < expCount - 1; i++) {
                    expInfo[i] = expInfo[i + 1];
                }
                expCount--;
                System.out.println();
                System.out.println("                      Expense deleted successfully!");

                System.out.print("            Do you want to delete another expense? (y/n): ");
                String choice = scan.next();

                while (true) {
                    if (choice.equalsIgnoreCase("y")) {
                        break;
                    } else if (choice.equalsIgnoreCase("n")) {
                        clearConsole();
                        homePg();
                    } else {
                        System.out.print("Invalid input! Do you want to delete another expense? (y/n): ");
                        choice = scan.next();
                    }
                }
            }
        }
    }

    public static void viewAllExp() {
        clearConsole();
        System.out.println("---------------------------------------------------------------");
        System.out.println("|                      VIEW ALL EXPENSES                       |");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("|%-10s| %-10s| %-10s| %-10s              |%n", "ID", "Amount", "Date", "Description");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < expCount; i++) {
            System.out.printf("|%-10s| %-10s| %-10s| %-10s               |%n", expInfo[i][0], expInfo[i][1],
                    expInfo[i][2], expInfo[i][3]);
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------");
        back();
    }

    public static void allExpSummary() {
        clearConsole();
        System.out.println("---------------------------------------------------------------");
        System.out.println("|                      ALL EXPENSES SUMMARY                   |");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("|%-10s| %-10s| %-10s                           |%n", "ID", "Amount", "Date");
        System.out.println("---------------------------------------------------------------");
        int tot = 0;

        for (int i = 0; i < expCount; i++) {
            int amount = Integer.parseInt(expInfo[i][1]);
            tot += amount;

            System.out.printf("|%-10s| %-10s| %-10s                           |%n", expInfo[i][0], expInfo[i][1],
                    expInfo[i][2]);
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------");
        System.out.println("Total Expenses: " + tot);
        back();
    }

    public static void monthlyExpSummary() {
        clearConsole();
        System.out.println("                    MONTHLY EXPENSE SUMMARY");
        System.out.println("-------------------------------------------------------\n");

        if (expCount == 0) {
            System.out.println("No expenses available to summarize.");
            back();
            return;
        }

        System.out.print("Enter Year (YYYY): ");
        int inputYear = scan.nextInt();
        System.out.print("Enter Month (MM): ");
        int inputMonth = scan.nextInt();

        int totalMonthlyExpense = 0;
        boolean found = false;

        System.out.println("\nExpenses for " + inputYear + "-" + (inputMonth < 10 ? "0" + inputMonth : inputMonth) + ":");

        for (int i = 0; i < expCount; i++) {
            String[] parts = expInfo[i][2].split("-"); //Assuming date format is YYYY-MM-DD
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);

            if (year == inputYear && month == inputMonth) {
                System.out.println("Date: " + expInfo[i][2] + " | Amount: " + expInfo[i][1]);
                totalMonthlyExpense += Integer.parseInt(expInfo[i][1]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found for this month.");
        } else {
            System.out.println("\nTotal Monthly Expense: " + totalMonthlyExpense);
        }

        back();
    }

    public static void back() {
        System.out.print("Back (b): ");
        String back = scan.next();

        if (back.equalsIgnoreCase("b")) {
            clearConsole();
            homePg();
        } else {
            System.out.println("Invalid input!");
            back = scan.next();
        }
    }
}
