// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;

public class AutoBalance extends Command {
  
  private final SwerveSubsystem swerve;
  public final PIDController autoBalancePID = new PIDController(0.1, 0, 0);

  public AutoBalance(SwerveSubsystem swerve) 
  {
    this.swerve = swerve;
    addRequirements(swerve);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
      double output = autoBalancePID.calculate(swerve.gyro.getPitch(), 0);
      swerve.frontLeft.setSpeedDrive(output);
      swerve.frontRight.setSpeedDrive(output);
      swerve.backLeft.setSpeedDrive(output);
      swerve.backRight.setSpeedDrive(output);

      
      SmartDashboard.putNumber("AutoBalancePower", autoBalancePID.calculate(swerve.gyro.getPitch(), 0));

      
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    swerve.frontLeft.setSpeedDrive(0);
    swerve.frontRight.setSpeedDrive(0);
    swerve.backLeft.setSpeedDrive(0);
    swerve.backRight.setSpeedDrive(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return (swerve.gyro.getPitch() <= 1);
  }
}

