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
public class TeleOp extends LinearOpMode
{

    K9bot objRobot = new K9bot();

    ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);


    @Override
    public void runOpMode()
    {

        //Initial declaration of variables used in TeleOp
        float fltClawPosition = 1;
        boolean blnChangeSpeed = true;
        boolean blnStillPressed = false;

        objRobot.init(hardwareMap);

        //Declare motors without encoders(don't put in K9)
        objRobot.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        objRobot.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Start", "TeleOp Ready");
        telemetry.update();

        waitForStart();

        // Jewel
        objRobot.JSX.setPosition(.5);
        objRobot.JSY.setPosition(.5);

        while(opModeIsActive())
        {

            // Tank drive
            float fltLeft = gamepad1.left_stick_y;
            float fltRight = gamepad1.right_stick_y;

            if (gamepad1.x)
            {
                if(!blnStillPressed)
                {
                    blnChangeSpeed = !blnChangeSpeed;
                    blnStillPressed = true;
                }
            }
            else
            {
                blnStillPressed = false;
            }

            double blnSpeed = blnChangeSpeed ? 1 : .50;
            if(gamepad1.right_bumper)
            {
                objRobot.leftMotor.setPower(blnSpeed);
                objRobot.rightMotor.setPower(blnSpeed);
            }
            else if (gamepad1.left_bumper)
            {
                objRobot.leftMotor.setPower(-blnSpeed);
                objRobot.rightMotor.setPower(-blnSpeed);
            }
            else
            {
                objRobot.leftMotor.setPower(-fltLeft * blnSpeed);
                objRobot.rightMotor.setPower(-fltRight * blnSpeed);
            }

            // Claw
            if (gamepad2.y)
            {
                fltClawPosition += .01;
            } else
                {
                if (fltClawPosition <= 0) {
                    fltClawPosition = 0;
                } else
                {
                    fltClawPosition -= .01;
                }
            }
            objRobot.claw.setPosition(fltClawPosition);

            // Lift
            float fltLift = gamepad2.left_stick_y;
            objRobot.liftMotor.setPower(fltLift);

            // Gripper
            if (gamepad2.b)
            {
                //Closed
                objRobot.leftGripper.setPosition(0);
                objRobot.rightGripper.setPosition(1);
            } else if (gamepad2.x)
            {
                //Open More for collecting
                objRobot.leftGripper.setPosition(.4);
                objRobot.rightGripper.setPosition(.6);
            } else if (gamepad2.y)
            {
                //Open Less for moving away from the cryptobox
                objRobot.leftGripper.setPosition(.2);
                objRobot.rightGripper.setPosition(.8);
            }

            // Feedback
            telemetry.addData("Drive speed is ", (blnChangeSpeed) ? "100%" : "50%");
            telemetry.addData("Lift Motor(Glyph)",  "%.2f", fltLift);
            telemetry.update();
            // Pause for 40 mS each cycle = update 25 times a second.
            sleep(40);
        }
    }
}
