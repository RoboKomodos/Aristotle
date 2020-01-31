/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystem.driveTrain;
import frc.robot.subsystem.pneumatic;

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
  driveTrain m_driveTrain;
  pneumatic m_pneumatic;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    m_driveTrain = new driveTrain();
    m_pneumatic = new pneumatic();
    configureButtonBindings();
    //the command for the driving
    new RunCommand(()->m_driveTrain.ArcadeDrive(xbox.getY(Hand.kLeft), xbox.getX(Hand.kLeft)), m_driveTrain);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //while the analog button 3(RT) is pressed set drivespeed to .6 and back to 1 on release
    new AnalogButton(xbox, 3).whenPressed(new InstantCommand(()->m_driveTrain.setSpeed(Constants.mikhailModeSpeed),m_driveTrain)).whenReleased(new InstantCommand(()->m_driveTrain.setSpeed(1),m_driveTrain));
    //on the press of the left bumper extend the cylinder up
    new JoystickButton(xbox, 5).whenPressed(new StartEndCommand(m_pneumatic::extend, m_pneumatic::off, m_pneumatic).withTimeout(Constants.pneumaticTimeout));
    //on the press of the right bumper extend the cylinder up
    new JoystickButton(xbox, 6).whenPressed(new StartEndCommand(m_pneumatic::retract, m_pneumatic::off, m_pneumatic).withTimeout(Constants.pneumaticTimeout));
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
