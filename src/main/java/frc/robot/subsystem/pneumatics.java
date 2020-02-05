/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class pneumatics extends SubsystemBase {
  // Initializes the Double Solenoid object
  DoubleSolenoid solenoid = new DoubleSolenoid(Constants.forwardChannel, Constants.reverseChannel);

  /**
   * Pneumatics subsystem
   * Controls the Solenoid
   */
  public pneumatics() {

  }

  // Pushes arm Out
  public void extend(){
    solenoid.set(DoubleSolenoid.Value.kForward);
  } 

  // Pushes arm In
  public void retract(){
    solenoid.set(DoubleSolenoid.Value.kReverse);
  } 

  // Turns off Solenoid
  public void turnOff(){
    solenoid.set(DoubleSolenoid.Value.kOff);
  } 



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
