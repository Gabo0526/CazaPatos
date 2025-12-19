package com.vasconez.gabriel.cazapatos

import android.app.Activity
import android.os.Environment
import java.io.File

class ExternalFileManager(
    private val activity: Activity
) : FileHandler {

    private val fileName = "login_external.txt"

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        val directorio = activity.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val archivo = File(directorio, fileName)

        val contenido = "${datosAGrabar.first}\n${datosAGrabar.second}"
        archivo.writeText(contenido)
    }

    override fun ReadInformation(): Pair<String, String> {
        val directorio = activity.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val archivo = File(directorio, fileName)

        if (!archivo.exists()) return "" to ""

        val lineas = archivo.readLines()
        val email = lineas.getOrNull(0) ?: ""
        val password = lineas.getOrNull(1) ?: ""
        return email to password
    }
}
