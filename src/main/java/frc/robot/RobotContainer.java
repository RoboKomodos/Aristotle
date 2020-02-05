/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystem.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //init controller. use internal functions for getting button presses
  XboxController xbox = new XboxController(Constants.controllerport);
  // The robot's subsystems and commands are defined here...
  pneumatics m_pneumatic;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_pneumatic = new pneumatics();
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //using analog button, extends pneumatic
    new AnalogButton(xbox,Constants.pneumaticAxis,Constants.pneumaticUpDegree,Constants.pneumaticRange).whenPressed(new StartEndCommand(m_pneumatic::extend, m_pneumatic::turnOff, m_pneumatic).withTimeout(.5));
    //using analog button, retracts pneumatic 
    new AnalogButton(xbox,Constants.pneumaticAxis,Constants.pneumaticDownDegree,Constants.pneumaticRange).whenPressed(new StartEndCommand(m_pneumatic::retract, m_pneumatic::turnOff, m_pneumatic).withTimeout(.5));

  }
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
