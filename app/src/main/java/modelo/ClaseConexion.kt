package modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {

    fun cadenaConexion(): Connection? {
        try{

            val url = "jdbc:oracle:thin:@172.20.10.2:1521:xe"
            val usuario = "ferexpoo"
            val contrasena = "lesserafim"

            val connection = DriverManager.getConnection(url, usuario, contrasena)
            return connection
        }catch (error:Exception) {
            println("Este es el error: $error")
            return null
        }
    }
}