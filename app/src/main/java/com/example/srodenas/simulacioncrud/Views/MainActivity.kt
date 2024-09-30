package com.example.srodenas.simulacioncrud.Views

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.srodenas.simulacioncrud.Logic.Client
import com.example.srodenas.simulacioncrud.Logic.Controller
import com.example.srodenas.simulacioncrud.R

class MainActivity : AppCompatActivity(){
    private lateinit var myButtonAdd: ImageView
    private lateinit var myButtonUpdate: ImageView
    private lateinit var myButtonDel: ImageView
    private lateinit var myDialog : Dialog
    private val controller= Controller()

    companion object {
        const val TAG = "---SALIDA---"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log. d(TAG, "Esto es un ejemplo de log")
        start()
    }




    //Aquí comienza todo. Como si fuera nuestro main
    private fun start() {
        myButtonAdd = findViewById(R.id.myButtonAdd)   //Recuperamos la referencia en memoria del botón de la interfaz.
        myButtonUpdate = findViewById(R.id.myButtomEdit)
        myButtonDel = findViewById(R.id.myButtomDel)
        myDialog = Dialog(controller,
            { id, name ->
                clientAdd(id, name)
            },
            { id, name ->
                clientUpdate(id, name)
            },
            { id ->
                clientDel(id)
            })



        myButtonAdd.setOnClickListener{
            //acepta una lambda, por tanto es una referencia a una función anónima.
            myDialog.show(0)  //mostramos el dialogo que me permite la inserción de datos en los campos de edición.
        }

        myButtonUpdate.setOnClickListener{
            myDialog.show(1)  //mostramos el dialogo que me permite la edición de datos en los campos de edición.

        }

        myButtonDel.setOnClickListener( {
            myDialog.show(2)  //mostramos el dialogo que me permite la eliminación de datos en los campos de edición.

        })


    }


    fun clientAdd(id: Int, name: String){
        val newClient = Client (id, name)
        controller.ClientAddController(newClient)
        var msg =  "El cliente con id = $id, ha sido insertado correctamente"

        Log.d(TAG, msg)
        showConsoleData(msg)
    }

    fun clientDel(id: Int) {
        var msg = ""
        val delete = controller.ClientDelController(id)  //borramos

        if (delete)
             msg =  "El cliente con id = $id, ha sido eliminado correctamente"
        else
            msg = "El cliente con id = $id, no ha sido encontrado para eliminar"

        Log. d(TAG, msg)
        showConsoleData(msg)

    }



    fun clientUpdate(id: Int, name: String) {
        var msg = ""
        val update = controller.ClientUpdateController(id, name)  //borramos el 2.

        if (update)
            msg =  "El cliente con id = $id, ha sido actualizado correctamente"
        else
            msg = "El cliente con id = $id, no ha sido encontrado para actualizar"

        Log. d(TAG, msg)
        showConsoleData(msg)

    }

    fun showConsoleData(msg:String){
        val msg = controller.showData()
        Thread.sleep(2000)
        Log. d(TAG, msg)
    }
}