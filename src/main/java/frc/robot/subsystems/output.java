/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class output extends SubsystemBase {
  VictorSPX motor = new VictorSPX(Constants.outputMotorPort);
  /**
   * Creates a new output.
   */
  public output() {

  }

  public void startWheel(){
    motor.set(ControlMode.PercentOutput,-Constants.outputSpeed);
  }

  public void stopWheel(){
    motor.set(ControlMode.PercentOutput, 0.0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
