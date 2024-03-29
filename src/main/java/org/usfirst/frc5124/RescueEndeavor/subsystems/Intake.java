// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5124.RescueEndeavor.subsystems;


import org.usfirst.frc5124.RescueEndeavor.Robot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Intake extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_VictorSPX wheels;
    private DoubleSolenoid deployer;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Intake() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        wheels = new WPI_VictorSPX(7);
        
        
        
        deployer = new DoubleSolenoid(0, 2, 5);
        addChild("deployer",deployer);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void setPower(double power) {
        wheels.set(power);
    }

    public void setDeployed(boolean deployed) {
        if (!deployed && Robot.catapult.getCatapultIsUp()) {
            hasDeployed = false;
            Robot.catapult.setCatapult(false);
        }
        deployer.set(deployed ? Value.kForward : Value.kReverse);
        if (!deployed) {
            hasDeployed = false;
        } else {
            if (hasDeployed) {
                return;
            }
            if (!deployingNow) {
                millisDeploy = System.currentTimeMillis();
                deployingNow = true;
            } else if (System.currentTimeMillis() - millisDeploy > DEPLOY_TIME) {
                hasDeployed = true;
                deployingNow = false;
            }
        }
    }

    private boolean hasDeployed = false;
    private boolean deployingNow = false;
    private long millisDeploy;
    private final long DEPLOY_TIME = 250;

    public boolean getIntakeDeployed() {
        if (deployingNow) {
            if (System.currentTimeMillis() - millisDeploy > DEPLOY_TIME) {
                hasDeployed = true;
                deployingNow = false;
            }
        }
        return hasDeployed;
    }

}

