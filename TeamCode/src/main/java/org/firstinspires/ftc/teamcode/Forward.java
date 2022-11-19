package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Forward", group="Robot")
public class Forward extends LinearOpMode {

    /* Declare OpMode members. */
    RobotHardware robot = new RobotHardware();

    private ElapsedTime     runtime = new ElapsedTime();


    //static final double     FORWARD_SPEED = 1;
    // static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "hi");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.setAllMotorPowers(0.5);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("moving forward", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        robot.setAllMotorPowers(0);

        sleep(20000);


    }
}

