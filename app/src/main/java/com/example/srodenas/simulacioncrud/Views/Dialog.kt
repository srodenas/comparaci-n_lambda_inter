package com.example.srodenas.simulacioncrud.Views

import com.example.srodenas.simulacioncrud.Data.RepositoryClient
import com.example.srodenas.simulacioncrud.Logic.Controller
import com.google.android.material.animation.AnimatableView.Listener

//simulo un diálogo.
class Dialog(var controller: Controller,
    var clientAdd :  (Int, String) -> Unit,
    var  clientUpdate : (Int,  String)-> Unit,
    var  clientDel : (Int) -> Unit
    ) {


    private var action : Int = 0



    //muestra el dialogo
    fun show(typeAction : Int){
        val posibleName = "CAMBIADO"
        val posibleId = controller.devIdRandom() //me da un aleatorio. -1 está vacío para editar/borrar.
        when (typeAction){
            0 -> onAccept() //simulamos que ahora pulsamos el botón aceptar de un nuevo

            1 ->
                if (posibleId != -1)
                    onEdit(posibleId, "CAMBIADO")

            2 ->
                if (posibleId != -1)
                    onDelete(posibleId)

        }

    }

    private fun onDelete(id : Int) {
        clientDel(id)
    }

    private fun onEdit(id: Int, name : String) {
        clientUpdate(id, name)
    }

    //simula el pulsado del botón aceptar del dialogo. Este recoge los datos del usuario.
    private fun onAccept() {
        clientAdd(RepositoryClient.incrementPrimary(), "NUEVO CLIENTE")
    }
}