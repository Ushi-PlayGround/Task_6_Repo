import java.util.Scanner;

public class App3Remake{

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";


        final String DASHBOARD = "\u270B Welcome to Smart Banking System";
        final String CREATE_NEW_ACCOUNT = "➕ Create New Account";
        final String DEPOSITS = "\u2705 Deposits";
        final String WITHDRAWALS = "\u26D4 Withdrawals";
        final String TRANSFER = "\u23E9 Transfer";
        final String CHECK_BALANCE = "\u2753 Check A/C Balance";
        final String DELETE_ACCOUNT = "\u274C Delete Accounts";
        

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);
        final String CONTINUE_MSG = String.format("\t%s%s%s\n",COLOR_BLUE_BOLD,"%s",RESET);

        String accountNum;
        String name;
        Double intialDeposit;
        boolean valid;
        
        String[][] acArray = new String[0][];
        
        String screen = DASHBOARD;

        do{
            final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD, screen, RESET);
            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");
            
            switch(screen){ 
                case DASHBOARD:{
                    
                    System.out.println("\t[1]. Create New Account ");
                    System.out.println("\t[2]. Deposits ");
                    System.out.println("\t[3]. Withdrawals");
                    System.out.println("\t[4]. Transfer");
                    System.out.println("\t[5]. Check A/C Balance");
                    System.out.println("\t[6]. Delete Account");
                    System.out.println("\t[7]. Exit\n");
        
                    System.out.print("\tEnter an Option: ");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch(option){

                        case 1:screen = CREATE_NEW_ACCOUNT;break;
                        case 2:screen = DEPOSITS;break;
                        case 3:screen = WITHDRAWALS;break;
                        case 4:screen = TRANSFER;break;
                        case 5:screen = CHECK_BALANCE;break;
                        case 6:screen = DELETE_ACCOUNT;break;
                        case 7:
                            System.out.println(CLEAR);
                            System.exit(0);
                        default:continue;
                    }
                    break;
                
                }case CREATE_NEW_ACCOUNT:{

                    do{
                        valid = true;
                        accountNum = String.format("SDB-%05d",(acArray.length+1));
                        System.out.printf("Account Number: %s \n",accountNum);

                        System.out.print("Enter Name: ");
                        name = scanner.nextLine().strip();
                
                        if (name.isBlank()){
                            System.out.printf(ERROR_MSG, "Customer name can't be empty");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (name.contains(" ")){
                                continue;
                            }
                            if (!(Character.isLetter(name.charAt(i)))) {
                                System.out.printf(ERROR_MSG,"Invalid Input");
                                valid = false;
                                break;
                            }    
                        }

                    }while(!valid);

                    do{
                        valid = true;
                        System.out.print("Enter the Intial Deposit: ");
                        intialDeposit = scanner.nextDouble();
                        scanner.nextLine();

                        if (intialDeposit < 5000){
                            System.out.printf(ERROR_MSG,"Insufficient Amount. Please Deposit Above Rs.5000!");
                            valid = false;
                            System.out.print(CONTINUE_MSG);
                            if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                        }else{
                            System.out.printf(SUCCESS_MSG,String.format("%s : %s has been created an account successfully. \n",accountNum,name) );
                            valid = true;
                            
                        }

                    }while (!valid);

                    String[][] tempArray = new String[acArray.length +1][3];

                    for (int i = 0; i < acArray.length; i++) {
                        tempArray[i] = acArray[i];
                    }

                    tempArray[tempArray.length -1][0] = accountNum;
                    tempArray[tempArray.length -1][1] = name;
                    tempArray[tempArray.length -1][2] = intialDeposit + "";

                    acArray = tempArray;

                    System.out.printf(CONTINUE_MSG,"\tDo you want to continue adding (Y/n)?");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                    
                }
            
            }
            
        }while (true);

    }
}
