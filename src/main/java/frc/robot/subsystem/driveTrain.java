/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class driveTrain extends PIDSubsystem {
  /**
   * Creates a new driveTrain.
   */
  private final VictorSPX left = new VictorSPX(Constants.leftDrive);
  private final VictorSPX right = new VictorSPX(Constants.rightDrive);
  private double speed =1;
  public driveTrain() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
  }

  public void setSpeed(double s){
    speed = s;
  }

  public void ArcadeDrive(double yAxis,double xAxis){
    left.set(ControlMode.PercentOutput, speed*(yAxis+xAxis));
    right.set(ControlMode.PercentOutput, speed*(yAxis-xAxis));
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
