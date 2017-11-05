package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@Autonomous(name="Blue Autonomous Back", group="K9bot")
//@Disabled
public class Blue_Autonomous_Back extends LinearOpMode
{

    static private final boolean BLUE_DESIRED = true;
    K9bot robot = new K9bot();
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    @Override
    public void runOpMode()
    {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        robot.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Path 0",  "Starting at %7d :%7d",
                robot.leftMotor.getCurrentPosition(),
                robot.rightMotor.getCurrentPosition());
        telemetry.update();

        waitForStart();

        //Set servos to default positions
        robot.JSX.setPosition(.5);
        robot.JSY.setPosition(.7);
        robot.leftGripper.setPosition(0);
        robot.rightGripper.setPosition(1);
        robot.liftMotor.setPower(-1);
        sleep(1000);
        robot.liftMotor.setPower(0);

        ReadJewel(BLUE_DESIRED);

        encoderDrive(.5, -13, 13);//Turn Left
        encoderDrive(.5,  25, 25);//Forward
        encoderDrive(.5, 13.5, -13.5);//Turn Right
        encoderDrive(.5, 13, 13);//Forward
        encoderDrive(.5, -13.5, 13.5);//Turn Left
        encoderDrive(.5, 7, 7);//Forward

        robot.liftMotor.setPower(1);
        sleep(1000);
        robot.liftMotor.setPower(0);
        robot.leftGripper.setPosition(.4);
        robot.rightGripper.setPosition(.6);
        sleep(1000);

        encoderDrive(.1, 3, 3);//Forward

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    public void encoderDrive(double speed, double leftInches, double rightInches)
    {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive())
        {

            newLeftTarget = robot.leftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.leftMotor.setTargetPosition(newLeftTarget);
            robot.rightMotor.setTargetPosition(newRightTarget);

            robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            robot.leftMotor.setPower(Math.abs(speed));
            robot.rightMotor.setPower(Math.abs(speed));

            while (opModeIsActive() && (robot.leftMotor.isBusy() && robot.rightMotor.isBusy()))
            {

                telemetry.addData("Path 1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path 2",  "Running at %7d :%7d",
                        robot.leftMotor.getCurrentPosition(),
                        robot.rightMotor.getCurrentPosition());
                telemetry.update();
            }

            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);
            robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void ReadJewel(boolean JewelBlueDesired)
    {
        boolean SensorBlue;

        robot.colorSensor.enableLed(true);

        //set initial positions of JS2 and swing JS1 in between the balls
        robot.JSY.setPosition(0);
        robot.JSX.setPosition(.5);
        sleep(1500);

        if(robot.colorSensor.blue()  > robot.colorSensor.red())
        {
            sleep(500);
            SensorBlue = true;
        }
        else
        {
            sleep(500);
            SensorBlue = false;
        }

        telemetry.addData("Jewel is ", (SensorBlue) ? "BLUE" : "RED");
        telemetry.update();

        if(SensorBlue ^ JewelBlueDesired)
        {
            robot.JSX.setPosition(0);
        }
        else
        {
            robot.JSX.setPosition(1);
        }
        sleep(1000);
        robot.JSY.setPosition(.7);
        robot.JSX.setPosition(.5);
    }
}
