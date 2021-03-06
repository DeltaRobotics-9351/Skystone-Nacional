package org.firstinspires.ftc.team9351.autonomous.test;


import com.github.deltarobotics9351.deltadrive.drive.mecanum.EncoderDriveMecanum;
import com.github.deltarobotics9351.deltadrive.hardware.DeltaHardware;
import com.github.deltarobotics9351.deltadrive.parameters.EncoderDriveParameters;
import com.github.deltarobotics9351.deltadrive.utils.ChassisType;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team9351.hardware.Hardware;

//@Disabled
@Autonomous(name="Autonomo Encoder Test", group="TEST")
public class AutonomoEncoderTest extends LinearOpMode {

    private Hardware hdw;

    private DeltaHardware deltaHardware;

    private EncoderDriveMecanum encoderDrive;

    @Override
    public void runOpMode() {
        hdw = new Hardware(hardwareMap); //creamos el hardware
        hdw.initHardware(false); //lo inicializamos

        deltaHardware = new DeltaHardware(hardwareMap, hdw.wheelFrontLeft, hdw.wheelFrontRight, hdw.wheelBackLeft, hdw.wheelBackRight, ChassisType.mecanum);

        EncoderDriveParameters parameters = new EncoderDriveParameters();
        parameters.LEFT_WHEELS_TURBO = 0.7;
        parameters.RIGHT_WHEELS_TURBO = 0.9;

        encoderDrive = new EncoderDriveMecanum(deltaHardware, telemetry, this, parameters);

        telemetry.addData("[/!\\]", "AUTONOMO DE PRUEBA! NO ESTA HECHO PARA SER USADO EN UNA COMPETENCIA.");
        telemetry.update();

        //esperamos que el usuario presione <play> en la driver station
        waitForStart();

        //imuDrive.rotate(90, 0.4);
        //Salu2

        encoderDrive.forward(80, 0.5, 10);
    }


}