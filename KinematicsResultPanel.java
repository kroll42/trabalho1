import javax.swing.*;
import java.awt.*;

public class KinematicsResultPanel extends JPanel {
    private JLabel resultLabel;

    public KinematicsResultPanel() {
        resultLabel = new JLabel("Resultado: ");
        add(resultLabel);
    }

    public void displayResult(double result) {
        resultLabel.setText("Resultado: " + result);
    }

    public void displayError(String errorMessage) {
        resultLabel.setText("Erro: " + errorMessage);
    }
}
