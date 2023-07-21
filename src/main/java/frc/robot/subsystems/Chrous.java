package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Chrous extends SubsystemBase{
    private WPI_TalonFX FLDrive = new WPI_TalonFX(Constants.DriveConstants.kFrontLeftDriveMotorPort);
    private WPI_TalonFX RLDrive = new WPI_TalonFX(Constants.DriveConstants.kRearLeftDriveMotorPort);
    private WPI_TalonFX FRDrive = new WPI_TalonFX(Constants.DriveConstants.kFrontRightDriveMotorPort);
    private WPI_TalonFX RRDrive = new WPI_TalonFX(Constants.DriveConstants.kRearRightDriveMotorPort);

    private WPI_TalonFX FLTurning = new WPI_TalonFX(Constants.DriveConstants.kFrontLeftTurningMotorPort);
    private WPI_TalonFX RLTurning = new WPI_TalonFX(Constants.DriveConstants.kRearLeftTurningMotorPort);
    private WPI_TalonFX FRTurning = new WPI_TalonFX(Constants.DriveConstants.kFrontRightTurningMotorPort);
    private WPI_TalonFX RRTurning = new WPI_TalonFX(Constants.DriveConstants.kRearRightTurningMotorPort);

    private WPI_TalonFX Elevator = new WPI_TalonFX(25);

    private Orchestra singer = new Orchestra();

    public Chrous() {
        singer.addInstrument(FLDrive);
        singer.addInstrument(RLDrive);
        singer.addInstrument(FRDrive);
        singer.addInstrument(RRDrive);
        singer.addInstrument(FLTurning);
        singer.addInstrument(RLTurning);
        singer.addInstrument(FRTurning);
        singer.addInstrument(RRTurning);
        singer.addInstrument(Elevator);
        singer.loadMusic("rickroll.chrp");
    }

    public void rickroll() {
        if (singer.isPlaying()) {
          singer.play();
        } else {
          singer.stop();
          singer.play();
        }
    }
}
