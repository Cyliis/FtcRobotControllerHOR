package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class SampleOpMode extends OpMode {
    DcMotorEx leftMotor, rightMotor;
    Servo servoArm;
    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftWheelMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightWheelMotor");
        servoArm = hardwareMap.get(Servo.class, "Arm");
    }

    boolean lastLoopPress = false;
    int servoState=0;

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double rotation = gamepad1.left_trigger - gamepad1.right_trigger;

//        if(gamepad1.dpad_up) {
//            servoArm.setPosition(0.45);
//        } else if(gamepad1.dpad_down){
//            servoArm.setPosition(0);
//        }

        if(gamepad1.dpad_up && !lastLoopPress){
            if(servoState == 0) servoState = 1;
            else if(servoState == 1) servoState = 0;
        }

        lastLoopPress = gamepad1.dpad_up;

        switch(servoState)
        {
            case 0:
                servoArm.setPosition(0);
                break;
            case 1:
                servoArm.setPosition(0.75);
                break;
        }

        rightMotor.setPower(rotation + forward);
        leftMotor.setPower(-rotation + forward);
    }
}
