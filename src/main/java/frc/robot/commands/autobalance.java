// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.Timer;

import com.pathplanner.lib.PathPlannerTrajectory;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class autobalance extends SequentialCommandGroup {
  /** Creates a new autobalance. */
  public autobalance(Intake m_intake, Elevator m_Elevator,autoback mAutoback ,DriveSubsystem m_robotDrive,PathPlannerTrajectory Mid) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new InstantCommand(()->m_intake.shootangleback()));

    addCommands(new InstantCommand(()->m_robotDrive.correctyaw(),m_robotDrive));

    addCommands(new WaitCommand(0.8).andThen(()->m_Elevator.level4(),m_Elevator));
    addCommands(new WaitCommand(0.8).andThen(()->m_intake.autoshootangle(),m_intake));

    addCommands(new WaitCommand(1.5).andThen(()->m_intake.shootcone(),m_intake));

    addCommands(new WaitCommand(1.1).andThen(mAutoback));

    addCommands(new WaitCommand(1.1).andThen(m_robotDrive.followTrajectoryCommand(Mid, true)).withTimeout(3));

    addCommands(new WaitCommand(0.1).andThen(()->m_robotDrive.drive(0,0.1,0,false,false)));
    
    addCommands(new WaitCommand(0.2).andThen(()->m_robotDrive.drive(0,0,0,false,false)));
    // addCommands(new WaitCommand(0.3).andThen(()->m_robotDrive.level(),m_robotDrive).withTimeout(7));
    
  }
}
