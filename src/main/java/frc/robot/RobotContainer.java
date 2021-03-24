/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.sequences.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //init controller
  XboxController xbox = new XboxController(Constants.controllerport);
  
  // The robot's subsystems and commands are defined here...
  pneumatics m_pneumatic;
  Elevator m_elevator;
  driveTrain m_driveTrain;
  output m_output; //output
  input m_input;



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_pneumatic = new pneumatics();
    m_elevator = new Elevator();
    //init output object
    m_output = new output();
    m_driveTrain = new driveTrain();
    m_input = new input(); // Initializes Input
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

    //Press "Select" to toggle the Elevator Motor, runs "toggler()"
    new JoystickButton(xbox, Button.kBack.value).whenPressed(new InstantCommand(m_elevator::toggler,m_elevator));
    // Sets the drivetrain to always fetch the joystick position
    m_driveTrain.setDefaultCommand(new RunCommand(()->m_driveTrain.arcadeDrive(
      // Applies the deadzone to the left joystick position
      deadzone(xbox.getX(GenericHID.Hand.kLeft)),
      deadzone(xbox.getY(GenericHID.Hand.kLeft))
    ),m_driveTrain));
    //When the right trigger is pressed, the robot will be in Mikhail mode
    new AnalogButton(xbox, 3).whenPressed(new InstantCommand(()->m_driveTrain.setSpeed(Constants.mikhailSpeed))).whenReleased(new InstantCommand(()->m_driveTrain.setSpeed(1)));
    //binds y to output and runs when held
    new JoystickButton(xbox, Button.kY.value).whenPressed(new InstantCommand(m_output::startWheel, m_output)).whenReleased(new InstantCommand(m_output::stopWheel, m_output));
    // Maps the Intake spinny thing to the 'A' button
    new JoystickButton( xbox, Button.kA.value).whenPressed(new InstantCommand(m_input::startSpin, m_input)).whenReleased(new InstantCommand(m_input::stopSpin, m_input));
    new JoystickButton(xbox, Button.kStart.value).whenPressed(new SlalomPath(m_driveTrain));
  }
  /**
   * Removes the oscillation of joystick positions close to zero
   * @param position is the joystick value between -1 and 1 for the x or y axis
   * @return double
   */
  private double deadzone(double position){
    double sign = position/Math.abs(position);
    return Math.abs(position)<=Constants.deadzone?0:sign*(Math.abs(position)-Constants.deadzone)/(1-Constants.deadzone);
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
