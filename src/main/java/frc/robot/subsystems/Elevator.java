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

public class Elevator extends SubsystemBase {

  //motor is the motor controller for elevator. instantiation of VictorSPX
  VictorSPX motor = new VictorSPX(Constants.elevatorPort);

  //boolean to toggle the state (on/off) of the motor
  private boolean toggle = false;

  /**
   * Creates a new Elevator.
   */
  public Elevator() {

  }

  /**
   * Toggles "toggle" to activate onSwitch & offSwitch
   */
  public void toggler(){
    toggle ^= true; //toggle boolean
    //turn motor on/off based on boolean state
    if(toggle == true){
      onSwitch();
    }
    else{
      offSwitch();
    }
  }

  /**
   * Turns on Elevator Motor
   */
  public void onSwitch() {
    motor.set(ControlMode.PercentOutput, -Constants.elevatorSpeed);
  }

  /**
   * Turns off Elevator Motor
   */
  public void offSwitch() {
    motor.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
