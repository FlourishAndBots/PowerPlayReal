package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file works in conjunction with the External Hardware Class sample called: ConceptExternalHardwareClass.java
 * Please read the explanations in that Sample about how to use this class definition.
 *
 * This file defines a Java Class that performs all the setup and configuration for a sample robot's hardware (motors and sensors).
 * It assumes three motors (left_drive, right_drive and arm) and two servos (left_hand and right_hand)
 *
 * This one file/class can be used by ALL of your OpModes without having to cut & paste the code each time.
 *
 * Where possible, the actual hardware objects are "abstracted" (or hidden) so the OpMode code just makes calls into the class,
 * rather than accessing the internal hardware directly. This is why the objects are declared "private".
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with *exactly the same name*.
 *
 * Or.. In OnBot Java, add a new file named HardwareRobot.java, drawing from this Sample; select Not an OpMode.
 * Also add a new OpMode, drawing from the Sample ConceptExternalHardwareClass.java; select TeleOp.
 *
 */

public class RobotHardware {

    // Define Motor and Servo objects  (Make them private so they can't be accessed externally)
    public DcMotor FrontRightMotor   = null;
    public DcMotor FrontLeftMotor  = null;
    public DcMotor BackRightMotor = null;
    public DcMotor BackLeftMotor = null;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();


    // Define a constructor that allows the OpMode to pass a reference to itself.
    public RobotHardware () {

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        // Define and Initialize Motors (note: need to use reference to actual OpMode).
        //control hub config names in green
        FrontRightMotor = hwMap.get(DcMotor.class, "FR");
        FrontLeftMotor = hwMap.get(DcMotor.class, "FL");
        BackRightMotor = hwMap.get(DcMotor.class, "BR");
        BackLeftMotor = hwMap.get(DcMotor.class, "BL");


        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        BackRightMotor.setDirection(DcMotor.Direction.REVERSE);
        BackLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        FrontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        FrontLeftMotor.setDirection(DcMotor.Direction.FORWARD);

        // If there are encoders connected, switch to RUN_USING_ENCODER mode for greater accuracy
        // leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        setMotorPowers(0);

    }
        public void setMotorPowers(double FRPower, double FLPower, double BRPower, double BLPower){
            FrontRightMotor.setPower(FRPower);
            FrontLeftMotor.setPower(FLPower);
            BackRightMotor.setPower(BRPower);
            BackLeftMotor.setPower(BLPower);
        }

        public void setMotorPowers(double allPower) {
            setMotorPowers(allPower, allPower, allPower, allPower);

        }



    }





