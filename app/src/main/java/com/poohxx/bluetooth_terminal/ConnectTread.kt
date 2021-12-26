package com.poohxx.bluetooth_terminal

import android.bluetooth.BluetoothA2dp
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.lang.Exception
import java.util.*

class ConnectThread(private val device: BluetoothDevice): Thread() {
    val uuid = "00001101-0000-1000-8000-00805F9B34FB"
    var mySocket: BluetoothSocket? = null
    lateinit var rThread: RecieveThread
    init {
        try {
            mySocket = device.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
        }catch (i:IOException){

        }
    }
    override fun run() {
     try {

         Log.d("LogDeb", "Connecting")
         mySocket?.connect()
         Log.d("LogDeb", "Connected")
         rThread= RecieveThread(mySocket!!)
         rThread.start()
     }catch (i:IOException){
         Log.d("LogDeb", "Not connected")
         closeConnection()

     }
    }
    fun closeConnection(){
        try {
            mySocket?.close()
        }catch (i: IOException){

        }

    }
}