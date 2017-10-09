package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="K9bot")
//@Disabled
public class TeleOp extends LinearOpMode {

    K9bot robot = new K9bot();

    @Override
    public void runOpMode() {
        double left = 0;
        double right = 0;
        double lift = 0;
        double clawPosition = 1;

        robot.init(hardwareMap);

        telemetry.addData("Start", "TeleOp Ready");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {

            robot.JS1.setPower(0);

            // Tank drive
            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;
            robot.leftMotor.setPower(-left);
            robot.rightMotor.setPower(-right);

            // Claw
            if(gamepad2.y){
                    clawPosition -= .01 ;
                }else{
                    clawPosition += .01 ;
                }
            robot.claw.setPosition(clawPosition);

            // Lift
            lift = gamepad2.left_stick_y;
            robot.liftMotor.setPower(-lift);

            // Gripper
            if(gamepad2.b) {
                robot.leftGripper.setPosition(1);
                robot.rightGripper.setPosition(0);
            }else{
                robot.leftGripper.setPosition(.7);
                robot.rightGripper.setPosition(.3);
            }

            telemetry.addData("Left Motor",  "%.2f", left);
            telemetry.addData("Right Motor",  "%.2f", right);
            telemetry.addData("Lift Motor(Glyph)",  "%.2f", lift);
            telemetry.update();

            // Pause for 40 mS each cycle = update 25 times a second.
            sleep(40);
        }
    }
}
