// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class hmposition extends SequentialCommandGroup {
  /** Creates a new hmposition. */
  public hmposition(Intake m_intake, Elevator m_Elevator) {
    addCommands(new InstantCommand(()->m_Elevator.hmlevel(),m_Elevator));
    addCommands(new WaitCommand(0.6).andThen(()->m_intake.hmconeangle(),m_intake));//hmconeangle
    // addCommands(new WaitCommand(0.3).andThen(()->m_intake.intakecone()));
  }
}
