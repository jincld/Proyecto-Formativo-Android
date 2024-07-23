package jonathan.orellana.fernanda.hernandez.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion

class detallepaciente : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detallepaciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //recibir valores de adpatador
        val nombre = intent.getStringExtra("nombre_rem")
        val apellido= intent.getStringExtra("apelllido_rem")
        val edad = intent.getStringExtra("edad_rem")
        val enfermedad= intent.getStringExtra("enfermedad_rem")
        val habitacion = intent.getStringExtra("numerode_habitacion")
        val cama = intent.getStringExtra("numerode_cama")
        val medicamentos = intent.getStringExtra("medicamentosasignas")
        val ingreso= intent.getStringExtra("fechadeingreso")
        val aplicacion= intent.getStringExtra("horadeaplicacion")


        //mandae elementos a la pantalla

        val txtnombre = findViewById<TextView>(R.id.Nombredeatlle)
        val txtapellido = findViewById<TextView>(R.id.apellidodetalle)
        val txtedad = findViewById<TextView>(R.id.edaddetalle)
        val txtenfermedad = findViewById<TextView>(R.id.enfermedadetalle)
        val txthabitacion = findViewById<TextView>(R.id.habitaciondetalle)
        val txtcama = findViewById<TextView>(R.id.camadetalle)
        val txtmedicamentos = findViewById<TextView>(R.id.medicdetalles)
        val txtingreso = findViewById<TextView>(R.id.horadetalle)
        val txtaplicacion = findViewById<TextView>(R.id.aplicaciondetalle)




        txtnombre.text = nombre
        txtapellido.text = apellido
        txtedad.text= edad
        txtenfermedad.text = enfermedad
       txthabitacion.text = habitacion
        txtcama.text = cama
        txtmedicamentos.text = medicamentos
        txtingreso.text = ingreso
        txtaplicacion.text = aplicacion






        }

    }

