package com.chopshop166.chopshoplib.logging.data;

import org.littletonrobotics.junction.LogTable;
import com.chopshop166.chopshoplib.logging.DataWrapper;
import com.chopshop166.chopshoplib.logging.LogName;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

/** Data for the entire swerve drive subsystem. */
public class SwerveDriveData extends DataWrapper {

    /** Front left swerve module. */
    public final SwerveModuleData frontLeft = new SwerveModuleData();
    /** Front right swerve module. */
    public final SwerveModuleData frontRight = new SwerveModuleData();
    /** Rear left swerve module. */
    public final SwerveModuleData rearLeft = new SwerveModuleData();
    /** Rear right swerve module. */
    public final SwerveModuleData rearRight = new SwerveModuleData();

    /** The heading of the robot. */
    @LogName("GyroYawPosition")
    public Rotation2d gyroYawPosition = new Rotation2d();

    /**
     * Set Desired States.
     * 
     * @param states The desired states.
     */
    public void setDesiredStates(final SwerveModuleState... states) {
        this.frontLeft.setDesiredState(states[0]);
        this.frontRight.setDesiredState(states[1]);
        this.rearLeft.setDesiredState(states[2]);
        this.rearRight.setDesiredState(states[3]);
    }

    /**
     * Get the current module positions.
     * 
     * @return The position objects as an array.
     */
    public SwerveModulePosition[] getModulePositions() {
        return new SwerveModulePosition[] {this.frontLeft.getModulePosition(),
                this.frontRight.getModulePosition(), this.rearLeft.getModulePosition(),
                this.rearRight.getModulePosition()};
    }

    /**
     * Get the states of all the modules.
     * 
     * @return The state objects as an array.
     */
    public SwerveModuleState[] getModuleStates() {
        return new SwerveModuleState[] {this.frontLeft.getModuleState(),
                this.frontRight.getModuleState(), this.rearLeft.getModuleState(),
                this.rearRight.getModuleState()};
    }

    @Override
    public void toLog(final LogTable table) {
        super.toLog(table);
        // We overload this so that we log these at a flatter level, for use with AdvantageScope.
        // We don't need to fromLog them, since this is just an accessor.
        table.put("States", this.getModuleStates());
    }

}
