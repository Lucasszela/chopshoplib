package com.chopshop166.chopshoplib.motors

import com.chopshop166.chopshoplib.sensors.IEncoder
import com.chopshop166.chopshoplib.sensors.MockEncoder
import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.revrobotics.CANSparkMax
import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj.motorcontrol.MotorController

fun SmartMotorController.toSmart() = this

fun MotorController.toSmart(encoder: IEncoder = MockEncoder()) =
    SmartMotorController(this, encoder)

fun CANSparkMax.follow(thisObj: CSSparkMax, inverted: Boolean = false) =
    follow(thisObj.motorController, inverted)

fun CANSparkMax.withPID(
    controlType: PIDControlType = PIDControlType.Velocity,
    block: CSSparkMax.() -> Unit = {}
) =
    CSSparkMax(this).apply {
        setControlType(controlType)
        block()
    }

fun WPI_TalonSRX.withPID(
    controlType: PIDControlType = PIDControlType.Velocity,
    block: WPI_TalonSRX.() -> Unit = {}
) =
    CSTalonSRX(this).apply {
        setControlType(controlType)
        block()
    }

fun WPI_TalonFX.withPID(
    controlType: PIDControlType = PIDControlType.Velocity,
    block: WPI_TalonFX.() -> Unit = {}
) =
    CSTalonFX(this).apply {
        setControlType(controlType)
        block()
    }

fun MotorController.withPID(
    pid: PIDController,
    encoder: IEncoder,
    controlType: PIDControlType = PIDControlType.Velocity,
    block: SwPIDMotorController.() -> Unit = {}
) =
    when (controlType) {
        PIDControlType.Position -> SwPIDMotorController.position(this, pid, encoder)
        PIDControlType.Velocity -> SwPIDMotorController.velocity(this, pid, encoder)
    }.apply(block)
