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
  private VictorSPX left;
  private VictorSPX right;
  private double speed = 1;
  public driveTrain() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
    left = new VictorSPX(Constants.leftmotor);
    right = new VictorSPX(Constants.rightmotor);
  }
  public void setSpeed(double speed){
    this.speed = speed;
  }
  public void arcadeDrive(double dx, double dy){
    left.set(ControlMode.PercentOutput, -speed*( dx+dy));
    right.set(ControlMode.PercentOutput, speed*( dy-dx));
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
