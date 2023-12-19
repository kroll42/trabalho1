import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KinematicsCalculatorUI {
    private JFrame frame;
    private JTextField velocityField;
    private JComboBox<String> velocityUnitComboBox;
    private JTextField launchAngleField;
    private JComboBox<String> angleUnitComboBox;
    private JTextField initialHeightField;
    private JComboBox<String> heightUnitComboBox;
    private JTextField flightTimeField;
    private JComboBox<String> timeUnitComboBox;
    private KinematicsResultPanel resultPanel;

    public void createAndShowGUI() {
        // configura interface gráfica
        frame = new JFrame("Calculadora Cinemática");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 2));

        // interface gráfica
        velocityField = new JTextField();
        velocityUnitComboBox = new JComboBox<>(new String[]{"m/s", "km/h", "ft/s", "mi/h"});
        launchAngleField = new JTextField();
        angleUnitComboBox = new JComboBox<>(new String[]{"graus", "radianos"});
        initialHeightField = new JTextField();
        heightUnitComboBox = new JComboBox<>(new String[]{"m", "km", "ft", "mi", "cm", "yd"});
        flightTimeField = new JTextField();
        timeUnitComboBox = new JComboBox<>(new String[]{"segundos", "horas", "minutos", "min/seg", "h/m/s"});

        JButton calculateButton = new JButton("Calcular");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateKinematics();
            }
        });

        resultPanel = new KinematicsResultPanel();

        frame.add(new JLabel("Velocidade Inicial:"));
        frame.add(velocityField);
        frame.add(new JLabel("Unidade:"));
        frame.add(velocityUnitComboBox);
        frame.add(new JLabel("Ângulo de Lançamento:"));
        frame.add(launchAngleField);
        frame.add(new JLabel("Unidade:"));
        frame.add(angleUnitComboBox);
        frame.add(new JLabel("Altura Inicial:"));
        frame.add(initialHeightField);
        frame.add(new JLabel("Unidade:"));
        frame.add(heightUnitComboBox);
        frame.add(new JLabel("Tempo de Voo:"));
        frame.add(flightTimeField);
        frame.add(new JLabel("Unidade:"));
        frame.add(timeUnitComboBox);
        frame.add(calculateButton);
        frame.add(resultPanel);

        frame.pack();
        frame.setVisible(true);
    }

    private void calculateKinematics() {
        try {
            double velocity = convertToMetersPerSecond(Double.parseDouble(velocityField.getText()), velocityUnitComboBox.getSelectedItem().toString());
            double launchAngle = convertToRadians(Double.parseDouble(launchAngleField.getText()), angleUnitComboBox.getSelectedItem().toString());
            double initialHeight = convertToMeters(Double.parseDouble(initialHeightField.getText()), heightUnitComboBox.getSelectedItem().toString());
            double flightTime = convertToSeconds(Double.parseDouble(flightTimeField.getText()), timeUnitComboBox.getSelectedItem().toString());

            double result = KinematicsCalculator.calculate(velocity, launchAngle, initialHeight, flightTime);

            resultPanel.displayResult(result, velocityUnitComboBox.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira números válidos.");
        } catch (IllegalArgumentException e) {
            resultPanel.displayError(e.getMessage());
        }
    }

    private double convertToMetersPerSecond(double value, String unit) {
        switch (unit) {
            case "m/s":
                return value;
            case "km/h":
                return value * 1000 / 3600;
            case "ft/s":
                return value * 0.3048;
            case "mi/h":
                return value * 1609.34 / 3600;
            default:
                throw new IllegalArgumentException("Unidade de velocidade não suportada: " + unit);
        }
    }

    private double convertToRadians(double value, String unit) {
        switch (unit) {
            case "graus":
                return Math.toRadians(value);
            case "radianos":
                return value;
            default:
                throw new IllegalArgumentException("Unidade de ângulo não suportada: " + unit);
        }
    }

    private double convertToMeters(double value, String unit) {
        switch (unit) {
            case "m":
                return value;
            case "km":
                return value * 1000;
            case "ft":
                return value * 0.3048;
            case "mi":
                return value * 1609.34;
            case "cm":
                return value * 0.01;
            case "yd":
                return value * 0.9144;
            default:
                throw new IllegalArgumentException("Unidade de altura não suportada: " + unit);
        }
    }

    private double convertToSeconds(double value, String unit) {
        switch (unit) {
            case "segundos":
                return value;
            case "horas":
                return value * 3600;
            case "minutos":
                return value * 60;
            case "min/seg":
                //formato mm:ss
                String stringValue = Double.toString(value);
                String[] parts = stringValue.split(":");
                return Double.parseDouble(parts[0]) * 60 + Double.parseDouble(parts[1]);
            case "h/m/s":
                // formato hh:mm:ss
                String stringValue2 = Double.toString(value);
                String[] parts2 = stringValue2.split(":");
                return Double.parseDouble(parts2[0]) * 3600 + Double.parseDouble(parts2[1]) * 60 + Double.parseDouble(parts2[2]);
            default:
                throw new IllegalArgumentException("Unidade de tempo não suportada: " + unit);
        }
    }
    
}
