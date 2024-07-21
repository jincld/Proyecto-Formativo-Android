package ViewHolderHelpers

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jonathan.orellana.fernanda.hernandez.myapplication.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var txtNombresCard = view.findViewById<TextView>(R.id.txtNombreCard)
    /*var txtApellidos = view.findViewById<TextView>(R.id.txtApellidos)
    var txtEdad = view.findViewById<TextView>(R.id.txtEdad)
    var txtCama = view.findViewById<TextView>(R.id.txtCama)
    var txtHabitacion = view.findViewById<TextView>(R.id.txtHabitacion)
    var txtEnfermedades = view.findViewById<TextView>(R.id.txtEnfermedades)
    var txtMedicamentos = view.findViewById<TextView>(R.id.txtMedicamentos)
    var txtAplicacion = view.findViewById<TextView>(R.id.txtAplicacion)
    var txtIngreso = view.findViewById<TextView>(R.id.txtIngreso)*/
    var btnAgregar = view.findViewById<Button>(R.id.btnAgregar)
    var btnEliminarCard = view.findViewById<ImageButton>(R.id.btnBorrarCard)
    var btnEditarCard = view.findViewById<ImageButton>(R.id.btnEditarCard)
}