import java.util.Scanner;

public class DayOfAMonth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int month = -1;
        int year = -1;
        while (true) {
            System.out.print("Enter a month (name, abbreviation, or number 1-12): ");
            String monthInput = sc.nextLine().trim();
            month = parseMonth(monthInput);
            
            if (month == -1) {
                System.out.println("Invalid month. Please try again.");
                continue;
            }
            System.out.print("Enter a year (non-negative, 4 digits): ");
            String yearInput = sc.nextLine().trim();

            if (!yearInput.matches("\\d{4}")) {
                System.out.println("Invalid year. Please try again.");
                continue;
            }
            year = Integer.parseInt(yearInput);
            break; // valid input → exit loop
        }
        int days = getDaysInMonth(month, year);
        System.out.println("Month " + month + " of year " + year + " has " + days + " days.");
        sc.close();
    }
    private static int parseMonth(String input) {
        input = input.toLowerCase();
        switch (input) {
            case "1": case "january": case "jan": case "jan.":
                return 1;
            case "2": case "february": case "feb": case "feb.":
                return 2;
            case "3": case "march": case "mar": case "mar.":
                return 3;
            case "4": case "april": case "apr": case "apr.":
                return 4;
            case "5": case "may":
                return 5;
            case "6": case "june": case "jun": case "jun.":
                return 6;
            case "7": case "july": case "jul": case "jul.":
                return 7;
            case "8": case "august": case "aug": case "aug.":
                return 8;
            case "9": case "september": case "sep": case "sep.":
                return 9;
            case "10": case "october": case "oct": case "oct.":
                return 10;
            case "11": case "november": case "nov": case "nov.":
                return 11;
            case "12": case "december": case "dec": case "dec.":
                return 12;
            default:
                return -1;
        }
    }
    
    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1;
        }
    }
    
    private static boolean isLeapYear(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }
}