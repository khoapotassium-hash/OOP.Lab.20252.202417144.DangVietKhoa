import javax.swing.JOptionPane;
public class EquationAndEquationSystem {
    public static void main(String[] args) {
        String[] options = {"Linear equation with 1 variable",
                "Linear system with 2 variables", "Quadratic equation with 2 variables", "Exit"};

        int choice = JOptionPane.showOptionDialog(null,
                "Choose the type of equation to solve:",
                "Equation Solver", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (choice == 0) solveLinearEquation();
        else if (choice == 1) solveLinearSystem();
        else if (choice == 2) solveQuadraticEquation();
    }

    private static void solveLinearEquation() {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Enter a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Enter b:"));

        if (a == 0) {
            if (b == 0) JOptionPane.showMessageDialog(null,
                    "Infinitely many solutions.",
                    "Solution:",JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(null,
                    "No solution.",
                    "Solution:",JOptionPane.INFORMATION_MESSAGE);
        } else {
            double x = -b / a;
            JOptionPane.showMessageDialog(null,
                    "x = " + x,
                    "Solution:",JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private static void solveLinearSystem() {
        double a11 = Double.parseDouble(JOptionPane.showInputDialog("Enter a11:"));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("Enter a12:"));
        double b1 = Double.parseDouble(JOptionPane.showInputDialog("Enter b1:"));
        double a21 = Double.parseDouble(JOptionPane.showInputDialog("Enter a21:"));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("Enter a22:"));
        double b2 = Double.parseDouble(JOptionPane.showInputDialog("Enter b2:"));

        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if (D != 0) {
            JOptionPane.showMessageDialog(null,
                    "x1 = " + (D1 / D) + "\nx2 = " + (D2 / D),
                    "Solution:",JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (D1 == 0 && D2 == 0) JOptionPane.showMessageDialog(null,
                    "Infinitely many solutions.",
                    "Solution:",JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(null,
                    "No solution.",
                    "Solution:",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void solveQuadraticEquation() {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Enter a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Enter b:"));
        double c = Double.parseDouble(JOptionPane.showInputDialog("Enter c:"));

        if (a == 0) {
            // Falls back to linear: bx + c = 0
            if (b == 0) {
                if (c == 0) JOptionPane.showMessageDialog(null,
                        "Infinitely many solutions.",
                        "Solution:",JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(null,
                        "No solution.",
                        "Solution:",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "x = " + (-c / b),
                        "Solution:",JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta > 0) {
                JOptionPane.showMessageDialog(null,
                        "x1 = " + ((- b + (delta * 0.5)) / (2 * a)) + "\nx2 = " + ((- b - (delta * 0.5)) / (2 * a)),
                        "Solution:",JOptionPane.INFORMATION_MESSAGE);
            } else if (delta == 0) {
                JOptionPane.showMessageDialog(null,
                        "x = " + (-b / (2 * a)),
                        "Solution:",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "No real root.",
                        "Solution:",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}