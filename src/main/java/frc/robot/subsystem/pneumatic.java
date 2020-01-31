/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class pneumatic extends SubsystemBase {
  /**
   * Creates a new pneumatic.
   */
  private DoubleSolenoid m_solenoid;
  public pneumatic() {
    m_solenoid = new DoubleSolenoid(0,1);
  }
  /**
   * Extends the cylinder upward (~5psi)
   */
  public void extend(){
    m_solenoid.set(Value.kForward);
  }

  /**
   * Retract the cylinder backward (60psi)
   */
  public void retract(){
    m_solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  /**
   * Turn off the solenoid to not put undo strain.
   */
  public void off(){
    m_solenoid.set(DoubleSolenoid.Value.kOff);
  }
  
}
