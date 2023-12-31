// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import edu.wpi.first.cameraserver.CameraServer;
// import com.ctre.phoenix.Util;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.autoback;
import frc.robot.commands.autobalance;
import frc.robot.commands.autoleveltop;
import frc.robot.commands.autoshoot;
import frc.robot.commands.floorconeintake;
import frc.robot.commands.floorcubeintake;
import frc.robot.commands.hmposition;
import frc.robot.commands.levelmid;
import frc.robot.commands.leveltop;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Chrous;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Candle;

// import edu.wpi.first.math.controller.HolonomicDriveController;
// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.controller.ProfiledPIDController;
// import frc.robot.Constants.AutoConstants;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final Intake m_intake = new Intake();
  private final Elevator m_Elevator = new Elevator();
  private final Chrous m_Chrous = new Chrous();
  // private final Camera m_Camera = new Camera();
  private final PathPlannerTrajectory mblue1to1 = PathPlanner.loadPath("blue1-1", new PathConstraints(1, 1));
  private final PathPlannerTrajectory mblue1to2 = PathPlanner.loadPath("blue1-2", new PathConstraints(1, 1));
  private final PathPlannerTrajectory mblue1to3 = PathPlanner.loadPath("blue1-3", new PathConstraints(1, 1));
  private final PathPlannerTrajectory mblue2to1 = PathPlanner.loadPath("blue2-1", new PathConstraints(1, 1));
  private final PathPlannerTrajectory mblue2to2 = PathPlanner.loadPath("blue2-2", new PathConstraints(1, 1));
  private final PathPlannerTrajectory mblue3to1 = PathPlanner.loadPath("blue 3-1", new PathConstraints(1, 1));
  private final PathPlannerTrajectory mblue3to2 = PathPlanner.loadPath("blue 3-2", new PathConstraints(1, 1));
  private final PathPlannerTrajectory mblue3to3 = PathPlanner.loadPath("blue 3-3", new PathConstraints(3, 3));
  private final PathPlannerTrajectory mtest = PathPlanner.loadPath("test", new PathConstraints(10, 8.5));
  private final PathPlannerTrajectory mgoout = PathPlanner.loadPath("goout", new PathConstraints(3, 1.5));
  private final PathPlannerTrajectory Mid = PathPlanner.loadPath("mid", new PathConstraints(2, 1.8));
  
  private double speedrate = 3;
  // private final PathPlannerTrajectory mred1to3 = PathPlanner.loadPath("blue1-3", new PathConstraints(0.5, 1));
  
/*tele*/
  private final leveltop mLeveltop = new leveltop(m_intake, m_Elevator);
  private final levelmid mLevelmid = new levelmid(m_intake, m_Elevator);
  private final hmposition m_Hmposition = new hmposition(m_intake, m_Elevator); 
  private final floorconeintake mFloorconeintake = new floorconeintake(m_intake, m_Elevator);
  private final floorcubeintake mFloorcubeintake = new floorcubeintake(m_intake, m_Elevator);

/*auto */
  private final autoshoot mAutoshoot = new autoshoot(m_intake);
  private final autoback mAutoback = new autoback(m_intake, m_Elevator);
  // private final autoRight mAutoRight = new autoRight(m_intake, m_Elevator , mAutoback,m_robotDrive);
  // private final autoleveltop mAutoleveltop = new autoleveltop(m_intake, m_Elevator , mAutoback, m_robotDrive,mblue3to3);
  private final autobalance mAutobalance = new autobalance(m_intake, m_Elevator, mAutoback, m_robotDrive, Mid);
  
  // AutoRight m_AutoRight = new AutoRight(m_intake, m_arm, m_Elevator, mTakeIn, mTakeIn2, m_Back, m_robotDrive, mblue3to1, mblue3to2, mblue3to3, m_OutPut);
  // AutoMiddle m_AutoMiddle = new AutoMiddle(m_intake, m_arm, m_Elevator, mTakeIn, mTakeIn2, m_Back, m_robotDrive, mblue2to1, mblue2to2, m_OutPut);
  // The driver's controller
  XboxController Joystick = new XboxController(OIConstants.kJoystickPort);
  XboxController driverstation = new XboxController(OIConstants.kDriverstationPort);
  // Joystick m_Joystick = new Joystick(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    CameraServer.startAutomaticCapture();
    // Configure default commands
    // m_intake.setDefaultCommand(
    //   new RunCommand(()->
    //   m_intake.buttonoffsetdown(Joystick.getRightBumper()), m_intake)
    // );
    // m_intake.setDefaultCommand(
    //   new RunCommand(()->
    //   m_intake.buttonoffsetup(Joystick.getLeftBumper()), m_intake)
    // );

    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () ->
                m_robotDrive.drive(
                  Joystick.getLeftY()*-speedrate,
                  Joystick.getLeftX()*-speedrate,
                  Joystick.getRightX()*-speedrate,
                  true,
                  Joystick.getRawButton(5)
                  ),
            m_robotDrive));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {
  

    new JoystickButton(Joystick, 6).whileTrue(m_Hmposition);    
    new JoystickButton(driverstation, 1).whileTrue(Commands.run((m_intake::portalangle)));    

    


//得分
   new JoystickButton(driverstation, 5).onTrue(mFloorcubeintake);
   new JoystickButton(driverstation, 6).whileTrue(Commands.runOnce(m_intake::intakecube, m_intake));
   new JoystickButton(driverstation, 6).whileFalse(Commands.runOnce(m_intake::neoStop, m_intake));
   new JoystickButton(Joystick, 1).onTrue(mFloorconeintake);
   new JoystickButton(driverstation, 3).whileTrue(Commands.runOnce(m_intake::intakecone, m_intake));
   new JoystickButton(driverstation, 3).whileFalse(Commands.runOnce(m_intake::neoStop, m_intake));
   new JoystickButton(Joystick, 2).onTrue(mLevelmid);
   new JoystickButton(driverstation, 4).onTrue(mLeveltop);
   new JoystickButton(driverstation, 2).onTrue(Commands.runOnce(m_Elevator::back));
   new JoystickButton(driverstation, 2).onTrue(Commands.runOnce(m_intake::shootangleback));
//joystick button
   new JoystickButton(Joystick, 3).whileTrue(Commands.runOnce(m_intake::shootcone, m_intake));
   new JoystickButton(Joystick, 3).whileFalse(Commands.runOnce(m_intake::neoStop, m_intake));
   new JoystickButton(Joystick, 4).whileTrue(Commands.runOnce(m_intake::shootcube, m_intake));
   new JoystickButton(Joystick, 4).whileFalse(Commands.runOnce(m_intake::neoStop, m_intake));
   new JoystickButton(Joystick, 5).onTrue(Commands.runOnce(m_Chrous::rickroll, m_Chrous));

    // // new button binding by 黃瑀婕
    // new JoystickButton(Joystick, 1).onTrue(mLevelmid);
    // new JoystickButton(Joystick, 2).onTrue(mLeveltop);

    // new JoystickButton(driverstation, 1).onTrue(m_intake::);

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

    public Command getAutonomousCommand() { 
  return mAutobalance;
  // return mAutoleveltop;
  // return null;
  }
 }
