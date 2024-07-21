package modelo

import android.widget.Button
import android.widget.EditText
import jonathan.orellana.fernanda.hernandez.myapplication.R
import java.util.UUID

data class dataClassPacientes(
    var txtUUID: String,
    var txtNombres: String,
    var txtApellidos: String,
    var txtEdad: Int,
    var txtEnfermedades: String,
    var txtHabitacion: Int,
    var txtCama: Int,
    var txtMedicamentos: String,
    var txtIngreso: String,
    var txtAplicacion: String,
)
