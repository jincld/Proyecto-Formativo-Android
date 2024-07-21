package ViewHolderHelpers

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jonathan.orellana.fernanda.hernandez.myapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import modelo.dataClassPacientes

class Adaptador(var Datos: List<dataClassPacientes>): RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paciente = Datos[position]
        holder.txtNombresCard.text = paciente.txtNombres
/*        holder.txtApellidos.text = paciente.txtApellidos
        holder.txtEdad.text = paciente.txtEdad.toString()
        holder.txtEnfermedades.text = paciente.txtEnfermedades
        holder.txtHabitacion.text = paciente.txtHabitacion.toString()
        holder.txtCama.text = paciente.txtCama.toString()
        holder.txtMedicamentos.text = paciente.txtMedicamentos
        holder.txtIngreso.text = paciente.txtIngreso
        holder.txtAplicacion.text = paciente.txtAplicacion*/

        //todo: click icono eliminar
        holder.btnEliminarCard.setOnClickListener() {

            val context = holder.itemView.context

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Eliminar")
            builder.setMessage("¿Quiere eliminar el paciente?")

            //Botones

            builder.setPositiveButton("Si") { dialog, which ->
                eliminarDatos(paciente.txtNombres, position)
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }

    fun eliminarDatos(txtNombres: String, position: Int) {

        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(position)

        GlobalScope.launch(Dispatchers.IO) {
            val objConexion = ClaseConexion().cadenaConexion()

            val borrarPac = objConexion?.prepareStatement("delete from tbpacientesrem where nombre_rem = ?")!!
            borrarPac.setString(1, txtNombres)
            borrarPac.executeUpdate()

            val commit = objConexion?.prepareStatement("commit")!!
            commit.executeUpdate()
        }

        Datos = listaDatos.toList()
        notifyItemRemoved(position)
        notifyDataSetChanged()

    }

}