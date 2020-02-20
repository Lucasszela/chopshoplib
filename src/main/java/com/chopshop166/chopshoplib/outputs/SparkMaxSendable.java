package com.chopshop166.chopshoplib.outputs;

import com.chopshop166.chopshoplib.sensors.SparkMaxEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * This wraps CANSparkMax and makes it sendable.
 * 
 * The same properties as other SpeedControllers are used for the Dashboard
 * representation. This allows reading and setting the motor speed from the
 * dashboard.
 */
public class SparkMaxSendable implements SendableSpeedController {
    final private CANSparkMax sparkMax;

    public SparkMaxSendable(final CANSparkMax spark) {
        this.sparkMax = spark;
    }

    /**
     * Get the wrapped encoder.
     * 
     * @return The encoder as the appropriate type.
     */
    public SparkMaxEncoder getEncoder() {
        return new SparkMaxEncoder(sparkMax.getEncoder());
    }

    @Override
    public void pidWrite(final double output) {
        sparkMax.pidWrite(output);
    }

    @Override
    public void initSendable(final SendableBuilder builder) {
        builder.setSmartDashboardType("Speed Controller");
        builder.setActuator(true);
        builder.setSafeState(this::disable);
        builder.addDoubleProperty("Value", this::get, this::set);
    }

    @Override
    public void set(final double speed) {
        sparkMax.set(speed);
    }

    @Override
    public double get() {
        return sparkMax.get();
    }

    @Override
    public void setInverted(final boolean isInverted) {
        sparkMax.setInverted(isInverted);
    }

    @Override
    public boolean getInverted() {
        return sparkMax.getInverted();
    }

    @Override
    public void disable() {
        sparkMax.disable();
    }

    @Override
    public void stopMotor() {
        sparkMax.stopMotor();
    }
}