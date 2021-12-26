package com.poohxx.bluetooth_terminal

import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class RecieveThread(val bsocket: BluetoothSocket):Thread() {
    var inStream:InputStream? = null
    var outStream:OutputStream? = null
    init {
        try {
            inStream = bsocket.inputStream
        }
        catch (i:IOException){

        }
        try {
            outStream = bsocket.outputStream
        }catch (i:IOException){

        }

    }

    override fun run() {
        val buf = ByteArray(2)
        while (true){
            try {
                val size = inStream?.read(buf)
                val message = String(buf, 0, size!!)
                Log.d("LogDeb", "message: $message")
            }catch (i:IOException){
                break
            }
        }
    }
    fun sendMessage(byteArray: ByteArray){
        try {
            outStream?.write(byteArray)
        }catch (i:IOException){

        }
    }
}