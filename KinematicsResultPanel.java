import javax.swing.*;
import java.awt.*;

public class KinematicsResultPanel extends JPanel {
    private JLabel resultLabel;

    public KinematicsResultPanel() {
        resultLabel = new JLabel("Resultado: ");
        add(resultLabel);
    }

    public void displayResult(double result, String unit) {
        String formattedResult = formatResult(result, unit);
        resultLabel.setText("Resultado: " + formattedResult);
    }

    public void displayError(String errorMessage) {
        resultLabel.setText("Erro: " + errorMessage);
    }

    private String formatResult(double result, String unit) {
        switch (unit) {
            case "m/s":
                return String.format("%.2f m/s", result);
            case "km/h":
                return String.format("%.2f km/h", result);
            case "ft/s":
                return String.format("%.2f ft/s", result);
            case "mi/h":
                return String.format("%.2f mi/h", result);
            case "graus":
                return String.format("%.2f graus", result);
            case "radianos":
                return String.format("%.2f radianos", result);
            case "segundos":
            	return String.format("%.2f segundos", result);
            case "minutos":
            	return String.format("%.2f minutos", result);
            case "horas":
            	return String.format("%.2f horas", result);
            default:
                return Double.toString(result); // Se a unidade não for reconhecida, mostrar apenas o número
        }
    }
}
