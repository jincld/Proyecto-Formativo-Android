package ViewHolderHelpers

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import jonathan.orellana.fernanda.hernandez.myapplication.R
import jonathan.orellana.fernanda.hernandez.myapplication.detallepaciente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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


        holder.itemView.setOnClickListener(){
            val context = holder.itemView.context

            val pantalladetalle = Intent(context, detallepaciente::class.java)

            pantalladetalle.putExtra("nombre_rem", paciente.txtNombres)
            pantalladetalle.putExtra("apellido_rem", paciente.txtApellidos)
            pantalladetalle.putExtra("edad_rem", paciente.txtEdad)
            pantalladetalle.putExtra("enfermedad_rem", paciente.txtEnfermedades)
            pantalladetalle.putExtra("numerode_habitacion", paciente.txtHabitacion)
            pantalladetalle.putExtra("numerode_cama", paciente.txtCama)
            pantalladetalle.putExtra("medicamentosasignas", paciente.txtMedicamentos)
            pantalladetalle.putExtra("horadeaplicacion", paciente.txtAplicacion)
            pantalladetalle.putExtra("fechadeingreso", paciente.txtIngreso)
            context.startActivity(pantalladetalle)
        }

        holder.btnEditarCard.setOnClickListener {
            val context = holder.itemView.context
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Editar Medicamentos")
            builder.setMessage("¿Desea editar los medicamentos?")

            //cuadro de texto para editar
            val cuadrotexto = EditText(context)
            cuadrotexto.setHint(paciente.txtMedicamentos)
            builder.setView(cuadrotexto)

            //botones
            builder.setPositiveButton("Confirmar") { dialog, which ->
                actualizardatos(cuadrotexto.text.toString(), paciente.txtApellidos)
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        holder.txtNombresCard.text = paciente.txtNombres
        holder.txtApellidos.text = paciente.txtApellidos
        holder.txtMedicamentos.text = paciente.txtMedicamentos

    /*        holder.txtEdad.text = paciente.txtEdad.toString()
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
    fun actualizarlista (nuevoList: List<dataClassPacientes>) {
        Datos = nuevoList
        notifyDataSetChanged()  //notifica al recycle view  que hay datos nuevos


    }
    fun actualizarpantalla (medicamentosNuevos:String, nuevoapellido: String){
        val index =Datos.indexOfFirst{it.txtApellidos == nuevoapellido}
        Datos[index].txtMedicamentos = medicamentosNuevos
        notifyItemChanged(index)
    }



    fun actualizardatos(medicamentosNuevos: String, nuevoapellido: String){
        GlobalScope.launch(Dispatchers.IO) {
            val objConexion = ClaseConexion().cadenaConexion()

            //VAIRABLE QUE TENGA UN PREPARESTATEMENT
            val updatepacientes = objConexion?.prepareStatement("update tbpacientesrem set medicamentosasignas = ? where apellido_rem = ?")!!
            updatepacientes.setString(1, medicamentosNuevos)
            updatepacientes.setString(2, nuevoapellido)
            updatepacientes.executeUpdate()

            val commit = objConexion.prepareStatement("commit")!!
            commit.executeUpdate()

            withContext(Dispatchers.Main){
                actualizarpantalla(medicamentosNuevos, nuevoapellido)


            }
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