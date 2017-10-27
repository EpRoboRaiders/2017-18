package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Autonomous", group="K9bot")
//@Disabled
public class Autonomous extends LinearOpMode {
    static private final boolean BLUE_DESIRED = true;
    K9bot robot = new K9bot();
    Jewel_Autonomous jewel_autonomous = new Jewel_Autonomous(robot);
    private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Start", "Autonomous Ready");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {
            // Jewel
            robot.JS1.setPosition(.5);
            robot.JS2.setPosition(.5);

            //jewel_autonomous.ReadJewel(BLUE_DESIRED);

            robot.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.leftMotor.setTargetPosition(5000);
            robot.rightMotor.setTargetPosition(5000);

            robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.leftMotor.setPower(1);
            robot.rightMotor.setPower(1);


            // Returns false when not moving then leaves While Statement
            while(robot.leftMotor.isBusy() && robot.rightMotor.isBusy()){
                int LPos = robot.leftMotor.getCurrentPosition();
                int RPos = robot.rightMotor.getCurrentPosition();
                telemetry.addData("Left Position = ", LPos);
                telemetry.addData("Right Position = ", RPos);
                telemetry.update();
                sleep(1000);
            }
            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);

        break;
        }
    }
}
