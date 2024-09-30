package com.example.srodenas.simulacioncrud.Views

import com.example.srodenas.simulacioncrud.Data.RepositoryClient
import com.example.srodenas.simulacioncrud.Logic.Controller
import com.example.srodenas.simulacioncrud.Logic.interfaces.OperationsInterface
import com.google.android.material.animation.AnimatableView.Listener

//simulo un diálogo.
class Dialog(var controller: Controller) {
    private var listener: OperationsInterface? = null  //Ya si eso lo creo.

    private var action : Int = 0

    //Carga el listener para el botón
    fun setListener ( _listener : OperationsInterface){
        listener = _listener

    }

    //muestra el dialogo
    fun show(typeAction : Int){
        listener?.let{
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
    }

    private fun onDelete(id : Int) {
        listener!!.ClientDel(id)
    }

    private fun onEdit(id: Int, name : String) {
        listener!!.ClientUpdate(id, name)
    }

    //simula el pulsado del botón aceptar del dialogo. Este recoge los datos del usuario.
    private fun onAccept() {
        listener!!.ClientAdd(RepositoryClient.incrementPrimary(), "NUEVO CLIENTE")
    }
}