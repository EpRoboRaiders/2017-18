package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.Math;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="K9bot")
//@Disabled
public class TeleOp extends LinearOpMode {

    K9bot robot = new K9bot();

    ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

    boolean stillPressed = false;
    @Override
    public void runOpMode() {

        double clawPosition = 1;
        boolean speed = true;

        robot.init(hardwareMap);

        robot.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Start", "TeleOp Ready");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()) {

            // Jewel
            robot.JS1.setPosition(.5);
            robot.JS2.setPosition(.5);

            // Tank drive
            double left = gamepad1.left_stick_y;
            double right = gamepad1.right_stick_y;

            if (gamepad1.x) {
                if(!stillPressed) {
                    speed = !speed;
                    stillPressed = true;
                }
            } else {
                stillPressed = false;
            }

            double slow = speed ? 1 : .50;
            if (gamepad1.right_bumper) {
                robot.leftMotor.setPower(slow);
                robot.rightMotor.setPower(slow);
            } else if (gamepad1.left_bumper) {
                robot.leftMotor.setPower(-slow);
                robot.rightMotor.setPower(-slow);
            } else {
                robot.leftMotor.setPower(-left * slow);
                robot.rightMotor.setPower(-right * slow);
            }

            // Claw
            if (gamepad2.y) {
                clawPosition += .01;
            } else {
                if (clawPosition <= 0) {
                    clawPosition = 0;
                } else {
                    clawPosition -= .01;
                }
            }
            robot.claw.setPosition(clawPosition);

            // Lift
            double lift = gamepad2.left_stick_y;
            robot.liftMotor.setPower(lift);

            // Intake
            if (gamepad2.right_bumper){
                //In
                robot.RintakeMotor.setPower(1);
                robot.LintakeMotor.setPower(1);
            } else if (gamepad2.left_bumper){
                //Out
                robot.RintakeMotor.setPower(-1);
                robot.LintakeMotor.setPower(-1);
            } else {
                robot.RintakeMotor.setPower(0);
                robot.LintakeMotor.setPower(0);
            }

            // Gripper
            if (gamepad2.b){
                robot.leftGripper.setPosition(0);
                robot.rightGripper.setPosition(1);
            } else if (gamepad2.x) {
                robot.leftGripper.setPosition(.3);
                robot.rightGripper.setPosition(.7);
            }

            // Feedback
            telemetry.addData("Drive speed is ", (speed) ? "100%" : "50%");
            telemetry.addData("Lift Motor(Glyph)",  "%.2f", lift);
            telemetry.update();
            // Pause for 40 mS each cycle = update 25 times a second.
            sleep(40);
        }
    }
}
