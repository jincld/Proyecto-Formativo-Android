package jonathan.orellana.fernanda.hernandez.myapplication.ui.agregar_paciente

import ViewHolderHelpers.Adaptador
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import jonathan.orellana.fernanda.hernandez.myapplication.R
import jonathan.orellana.fernanda.hernandez.myapplication.databinding.FragmentAgregarPacienteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.dataClassPacientes
import java.util.UUID

class fragment_agregar_paciente : Fragment() {

    private var _binding: FragmentAgregarPacienteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(agregarPacienteViewModel::class.java)

        _binding = FragmentAgregarPacienteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var txtNombres = root.findViewById<EditText>(R.id.nombre)
        var txtApellidos = root.findViewById<EditText>(R.id.txtApellidos)
        var txtEdad = root.findViewById<EditText>(R.id.txtEdad)
        var txtCama = root.findViewById<EditText>(R.id.txtCama)
        var txtHabitacion = root.findViewById<EditText>(R.id.txtHabitacion)
        var txtEnfermedades = root.findViewById<EditText>(R.id.txtEnfermedades)
        var txtMedicamentos = root.findViewById<EditText>(R.id.txtMedicamentos)
        var txtAplicacion = root.findViewById<EditText>(R.id.txtAplicacion)
        var txtIngreso = root.findViewById<EditText>(R.id.txtIngreso)
        var btnAgregar = root.findViewById<Button>(R.id.btnAgregar)



        /*fun obtenerdatos(): List<dataClassPacientes>{


            val objConexion = ClaseConexion().cadenaConexion()


            val statement = objConexion?.createStatement()
            val resulSet = statement?.executeQuery("select * from ticket") !!
            val paciente = mutableListOf<dataClassPacientes>()

            //recorro todos los registros de la base de datos
            while (resulSet.next())  {



                val nombre = resulSet.getString("nombre_rem")
                val apellido = resulSet.getString("apellido_rem")
                val edad = resulSet.getString("edad_rem").toString()
                val enfer = resulSet.getString("enfermedades_rem")
                val habit = resulSet.getString("numerode_habitacion").toString()
                val cama = resulSet.getString("numerode_cama").toString()
                val medic = resulSet.getString("medicamentosasignas")
                val ingreso = resulSet.getString("fechadeingreso")
                val aplic= resulSet.getString("horadeaplicacion")

                val pacientes = dataClassPacientes(nombre, apellido, edad, enfer, habit, cama, medic, ingreso, aplic)
                paciente.add(pacientes)
            }
      return paciente*/



        btnAgregar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val objConexion = ClaseConexion().cadenaConexion()

                val crearPaciente = objConexion?.prepareStatement("insert into tbpacientesrem (uuid_paciente, nombre_rem, apellido_rem, edad_rem, enfermedad_rem, numerode_habitacion, numerode_cama, medicamentosasignas, fechadeingreso, horadeaplicacion) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")!!
                crearPaciente.setString(1, UUID.randomUUID().toString())
                crearPaciente.setString(2, txtNombres.text.toString())
                crearPaciente.setString(3, txtApellidos.text.toString())
                crearPaciente.setString(4, txtEdad.text.toString())
                crearPaciente.setString(5, txtEnfermedades.text.toString())
                crearPaciente.setString(6, txtHabitacion.text.toString())
                crearPaciente.setString(7, txtCama.text.toString())
                crearPaciente.setString(8, txtMedicamentos.text.toString())
                crearPaciente.setString(9, txtIngreso.text.toString())
                crearPaciente.setString(10, txtAplicacion.text.toString())
                crearPaciente.executeUpdate()

                withContext(Dispatchers.Main){
                    Toast.makeText(context, "Paciente registrado", Toast.LENGTH_SHORT).show()
                    txtNombres.setText("")
                    txtApellidos.setText("")
                    txtEdad.setText("")
                    txtEnfermedades.setText("")
                    txtHabitacion.setText("")
                    txtCama.setText("")
                    txtMedicamentos.setText("")
                    txtIngreso.setText("")
                    txtAplicacion.setText("")
                }
            }

        }

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}