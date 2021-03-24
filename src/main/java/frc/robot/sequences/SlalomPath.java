// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autocommands.*;
import frc.robot.subsystems.driveTrain;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SlalomPath extends SequentialCommandGroup {
  /** Creates a new SlalomPath. */
  public SlalomPath(driveTrain drive) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new tightLeft(drive).withTimeout(0.5),
      new forward(drive).withTimeout(1),
      new slightRight(drive).withTimeout(2.5),
      new forward(drive).withTimeout(1),
      new tightLeft(drive).withTimeout(2),
      new forward(drive).withTimeout(0.5),
      new slightRight(drive).withTimeout(0.8),
      new forward(drive).withTimeout(2),
      new slightRight(drive).withTimeout(1),
      new slightLeft(drive).withTimeout(1),
      new stop(drive)
    );
  }
}
