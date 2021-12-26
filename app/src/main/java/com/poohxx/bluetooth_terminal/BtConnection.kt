package com.poohxx.bluetooth_terminal

import android.bluetooth.BluetoothAdapter
import android.os.Message

class BtConnection(private val adapter: BluetoothAdapter) {
   private lateinit var cThread: ConnectThread
    fun connect(mac:String){
        if(adapter.isEnabled && mac.isNotEmpty()) {
            val device = adapter.getRemoteDevice(mac)
            device.let {
              cThread = ConnectThread(it)
              cThread.start()

            }
        }
    }
    fun sendMessage(message: String) = cThread.rThread.sendMessage(message.toByteArray())
}