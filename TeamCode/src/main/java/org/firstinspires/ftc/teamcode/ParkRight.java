package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Park Right", group="Robot")
public class ParkRight extends LinearOpMode {

    /* Declare OpMode members. */
    RobotHardware robot = new RobotHardware();

    private ElapsedTime     runtime = new ElapsedTime();
    private CRServo Claw;


    //static final double     FORWARD_SPEED = 1;
    // static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);
        Claw = hardwareMap.get(CRServo.class, "Claw");
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "hi");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.setAllMotorPowers(0.5);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 0.3)) {
            telemetry.addData("moving forward", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        robot.setAllMotorPowers(0);
        sleep(1000);
        runtime.reset();

        robot.setMotorPowers(-0.5,0.5,0.5,-0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("moving forward", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }


     //   Claw.setPower(1);

        robot.setAllMotorPowers(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(20000);


    }
}
