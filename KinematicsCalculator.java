public class KinematicsCalculator {
    public static double calculate(double velocity, double launchAngle, double initialHeight, double flightTime) {
        if (velocity <= 0 || initialHeight < 0 || flightTime <= 0) {
            throw new IllegalArgumentException("Velocidade, altura inicial e tempo de voo devem ser valores positivos.");
        }

        if (flightTime == 0) {
            throw new IllegalArgumentException("O tempo de voo não pode ser zero.");
        }

        double timeInAir = flightTime;
        double gravity = 9.8; // gravidade

        if (launchAngle == 0) {
            return calculate1D(velocity, timeInAir);
        } else {
            return calculate2D(velocity, launchAngle, initialHeight, timeInAir, gravity);
        }
    }

    private static double calculate1D(double velocity, double time) {
        return velocity * time;
    }

    private static double calculate2D(double velocity, double launchAngle, double initialHeight, double time, double gravity) {
        double horizontalDistance = velocity * Math.cos(launchAngle) * time;
        double verticalDistance = (velocity * Math.sin(launchAngle) * time) - (0.5 * gravity * Math.pow(time, 2)) + initialHeight;

        return Math.sqrt(Math.pow(horizontalDistance, 2) + Math.pow(verticalDistance, 2));
    }
}
