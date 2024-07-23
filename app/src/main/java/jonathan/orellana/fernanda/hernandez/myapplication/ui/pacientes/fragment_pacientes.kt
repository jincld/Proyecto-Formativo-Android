package jonathan.orellana.fernanda.hernandez.myapplication.ui.pacientes

import ViewHolderHelpers.Adaptador
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jonathan.orellana.fernanda.hernandez.myapplication.R
import jonathan.orellana.fernanda.hernandez.myapplication.databinding.FragmentPacientesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.dataClassPacientes

class fragment_pacientes : Fragment() {

    private var _binding: FragmentPacientesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.fragment_pacientes, container, false)
        val rcvPacientes = root.findViewById<RecyclerView>(R.id.rcvPacientes)

        rcvPacientes.layoutManager = LinearLayoutManager(context)
        fun obtenerDatos(): List<dataClassPacientes>{

            //crear objeto conexion

            val objConexion = ClaseConexion().cadenaConexion()

            //crear statement

            val statement = objConexion?.createStatement()
            val resulSet = statement?.executeQuery("select * from tbpacientesrem")!!
            val pacientes = mutableListOf<dataClassPacientes>()

            while(resulSet.next()){
                val txtUUID = resulSet.getString("uuid_paciente")
                val txtNombres = resulSet.getString("nombre_rem")
                val txtApellidos = resulSet.getString("apellido_rem")
                val txtEdad = resulSet.getString("edad_rem")
                val txtEnfermedades = resulSet.getString("enfermedad_rem")
                val txtHabitacion = resulSet.getString("numerode_habitacion")
                val txtCama = resulSet.getString("numerode_cama")
                val txtMedicamentos = resulSet.getString("medicamentosasignas")
                val txtIngreso = resulSet.getString("fechadeingreso")
                val txtAplicacion = resulSet.getString("horadeaplicacion")



                val ValoresJuntos = dataClassPacientes(txtUUID, txtNombres, txtApellidos, txtEdad, txtEnfermedades, txtHabitacion, txtCama, txtMedicamentos, txtIngreso, txtAplicacion)
                pacientes.add(ValoresJuntos)


            }
            return pacientes
        }
        CoroutineScope(Dispatchers.IO).launch {
            val PacienteDB = obtenerDatos()
            withContext(Dispatchers.Main){
                val adapter = Adaptador(PacienteDB)
                rcvPacientes.adapter= adapter

            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}