/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class driveTrain extends PIDSubsystem {
  /**
   * Creates a new driveTrain.
   */
  private VictorSPX m_left;
  private VictorSPX m_right;
  private AnalogGyro m_gyro;
  private double m_speed = 1;
  public driveTrain() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
    m_left = new VictorSPX(Constants.leftmotor);
    m_right = new VictorSPX(Constants.rightmotor);
  }
  /**
   * Sets the speed of the motors
   * @param speed as a double between 0 and 1
   */
  public void setSpeed(double speed){
    m_speed = speed;
  }
  /**
   * Sets the motor speed and direction
   * @param dx the joystick x position
   * @param dy the joystick y position
   */
  public void arcadeDrive(double dx, double dy){
    m_left.set(ControlMode.PercentOutput, m_speed*( dx+dy));
    m_right.set(ControlMode.PercentOutput, m_speed*( dy-dx));
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
