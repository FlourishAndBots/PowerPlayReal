package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Robot: Auto Drive By Time", group="Robot")
public class TestAuto extends LinearOpMode {

    /* Declare OpMode members. */
    RobotHardware robot = new RobotHardware();

    private ElapsedTime     runtime = new ElapsedTime();


    //static final double     FORWARD_SPEED = 1;
   // static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {

      robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "hey lol");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

       // robot.setMotorPowers(FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED);

        runtime.reset();

        while (opModeIsActive() && runtime.milliseconds() < 5000){
            telemetry.addData("moving lol", "");
            telemetry.update();
        }

        robot.setMotorPowers(0.5, 0.5, 0.5, 0.5);
        sleep(5000);
        /*
        robot.setMotorPowers(0, 1, 0, 0);
        sleep(2000);
        robot.setMotorPowers(0, 0, 1, 0);
        sleep(2000);
        robot.setMotorPowers(0, 0, 0, 1);
        sleep(2000);
*/
        runtime.reset();

      //  robot.setMotorPowers(0.25, 1, 1, 1);
        //robot.setMotorPowers(-1);
        //sleep(1000);

        //robot.setMotorPowers(-TURN_SPEED, -TURN_SPEED, TURN_SPEED, TURN_SPEED);
       // runtime.reset();
       /*
        while (opModeIsActive() && runtime.milliseconds() < 3000){
            telemetry.addData("turning left", "");
            telemetry.update();
        }
        \
        */
        robot.setMotorPowers(0, 0, 0, 0);
        sleep(1000);
    }
}
