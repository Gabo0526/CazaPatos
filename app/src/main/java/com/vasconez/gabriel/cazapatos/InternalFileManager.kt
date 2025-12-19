package com.vasconez.gabriel.cazapatos

import android.app.Activity
import java.io.File

class InternalFileManager(
    private val activity: Activity
) : FileHandler {

    private val fileName = "login_data.txt"

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        val contenido = "${datosAGrabar.first}\n${datosAGrabar.second}"
        activity.openFileOutput(fileName, Activity.MODE_PRIVATE).use {
            it.write(contenido.toByteArray())
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        val file = File(activity.filesDir, fileName)
        if (!file.exists()) return "" to ""

        val lineas = file.readLines()
        val email = lineas.getOrNull(0) ?: ""
        val password = lineas.getOrNull(1) ?: ""
        return email to password
    }
}
