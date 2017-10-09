package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

        telemetry.addData("Start", "Autonomous Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            jewel_autonomous.ReadJewel(BLUE_DESIRED); // Runs the Jewel Autonomous(Jewel_Autonomous).
            break;
        }
    }
}
