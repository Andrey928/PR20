package com.example.pr20

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    lateinit var sensorManager: SensorManager
    lateinit var gyroscopeSensorListener: SensorEventListener
    fun btnClick(view: View){

        val Start : Button = findViewById(R.id.button)
        val Stop : Button = findViewById(R.id.button2)

        val tx : TextView = findViewById(R.id.textView10)
        val ty : TextView = findViewById(R.id.textView11)
        val tz : TextView = findViewById(R.id.textView12)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val gyroscopeSenser = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        gyroscopeSensorListener = object : SensorEventListener {
            override fun onSensorChanged(sensorEvent: SensorEvent) {

                var x = sensorEvent.values[0].toInt().toDouble()
                var y = sensorEvent.values[1].toInt().toDouble()
                var z = sensorEvent.values[2].toInt().toDouble()

                tx.setText(x.toString())
                ty.setText(y.toString())
                tz.setText(z.toString())
            }

            override fun onAccuracyChanged(sensor: Sensor, int: Int) {}

        }
        sensorManager.registerListener(
            gyroscopeSensorListener,
            gyroscopeSenser, SensorManager.SENSOR_DELAY_UI
        )
        Start.isEnabled = false
        Stop.isEnabled = true
    }

    fun bStop(view: View){
        sensorManager.unregisterListener(gyroscopeSensorListener)
        val Start: Button = findViewById(R.id.button)
        val Stop: Button = findViewById(R.id.button2)
        Start.isEnabled = true
        Stop.isEnabled = false
    }
}