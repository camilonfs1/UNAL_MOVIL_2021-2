package com.unal.webservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unal.multiplayertictactoe.BackCommunicationImp.domain.Interfaces.InterfaceService
import com.unal.multiplayertictactoe.Game.Adapter.AdapterRecyclerVertical
import com.unal.webservices.BackEndCommunication.Entities.MunicipioItem
import com.unal.webservices.BackEndCommunication.RestEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
// https://www.datos.gov.co/Ciencia-Tecnolog-a-e-Innovaci-n/Estudio-de-percepci-n-ciudadana-sobre-el-conocimie/rfqz-psdm
class MainActivity : AppCompatActivity() {
    var main_item_H: RecyclerView? = null
    var adaptador_V: AdapterRecyclerVertical? = null
    var layoutmanager: RecyclerView.LayoutManager? = null
    var butn: Button? = null


    private lateinit var edClasification: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_item_H = findViewById(R.id.recycler_vertical)
        butn = findViewById(R.id.btn)
        edClasification = findViewById(R.id.spin_clasification)


        butn!!.setOnClickListener {
            val clasification = edClasification!!.getSelectedItem().toString()

            when(clasification){
                ""->listItems()
                "Ha usado aplicaciones?"->listItemsAplicaciones()
                "Municipio"->listItemsMunicipio()
                "Tipo de empresa"->listItemsEmpresa()
            }
        }
        listItems()
    }

    private fun listItems(){
        val itemService : InterfaceService = RestEngine.getRestEngine().create(InterfaceService::class.java)
       CoroutineScope(Dispatchers.IO).launch {
            val response = itemService.listItems()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    var items_blog: ArrayList<MunicipioItem> = ArrayList()
                    var counter =  0
                    for(y in response.body()!!){

                        if(counter <=30){

                            val a=y.como_se_enteraron_de_esta
                            val b=y.donde_entrega_los_residuos
                            val c=y.entidad
                            val d=y.ha_participado_en_las_jornadas
                            val e=y.ha_usado_aplicaciones
                            val f=y.municipio
                            val g=y.sabe_que_significa_la_sigla
                            val h=y.tiene_residuos_postconsumo
                            val i=y.tipo_de_empresa
                            val j=y.utilizaria_una_app_para_raee
                            System.out.println(a.toString()+"----"+b)
                            var item = MunicipioItem(a,b,c,d,e,f,g,h,i,j)
                            items_blog!!.add(item)

                            counter = counter+1
                        }

                    }
                    v(items_blog!!)
                } else {
                    System.out.println("RETROFIT_ERROR")
                }
            }
        }
    }

    private fun listItemsMunicipio(){
        val itemService : InterfaceService = RestEngine.getRestEngine().create(InterfaceService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = itemService.listItemsMunicipio("TOLEDO")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    var items_blog: ArrayList<MunicipioItem> = ArrayList()
                    var counter =  0
                    for(y in response.body()!!){

                        if(counter <=30){

                            val a=y.como_se_enteraron_de_esta
                            val b=y.donde_entrega_los_residuos
                            val c=y.entidad
                            val d=y.ha_participado_en_las_jornadas
                            val e=y.ha_usado_aplicaciones
                            val f=y.municipio
                            val g=y.sabe_que_significa_la_sigla
                            val h=y.tiene_residuos_postconsumo
                            val i=y.tipo_de_empresa
                            val j=y.utilizaria_una_app_para_raee
                            System.out.println(a.toString()+"----"+b)
                            var item = MunicipioItem(a,b,c,d,e,f,g,h,i,j)
                            items_blog!!.add(item)

                            counter = counter+1
                        }

                    }
                    v(items_blog!!)
                } else {
                    System.out.println("RETROFIT_ERROR")
                }
            }
        }
    }

    private fun listItemsAplicaciones(){
        val itemService : InterfaceService = RestEngine.getRestEngine().create(InterfaceService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = itemService.listItemsAplicaciones("NO")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    var items_blog: ArrayList<MunicipioItem> = ArrayList()
                    var counter =  0
                    for(y in response.body()!!){

                        if(counter <=30){

                            val a=y.como_se_enteraron_de_esta
                            val b=y.donde_entrega_los_residuos
                            val c=y.entidad
                            val d=y.ha_participado_en_las_jornadas
                            val e=y.ha_usado_aplicaciones
                            val f=y.municipio
                            val g=y.sabe_que_significa_la_sigla
                            val h=y.tiene_residuos_postconsumo
                            val i=y.tipo_de_empresa
                            val j=y.utilizaria_una_app_para_raee
                            System.out.println(a.toString()+"----"+b)
                            var item = MunicipioItem(a,b,c,d,e,f,g,h,i,j)
                            items_blog!!.add(item)

                            counter = counter+1
                        }

                    }
                    v(items_blog!!)
                } else {
                    System.out.println("RETROFIT_ERROR")
                }
            }
        }
    }

    private fun listItemsEmpresa(){
        val itemService : InterfaceService = RestEngine.getRestEngine().create(InterfaceService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = itemService.listItemsEmpresa("ESTUDIANTE")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    var items_blog: ArrayList<MunicipioItem> = ArrayList()
                    var counter =  0
                    for(y in response.body()!!){

                        if(counter <=30){

                            val a=y.como_se_enteraron_de_esta
                            val b=y.donde_entrega_los_residuos
                            val c=y.entidad
                            val d=y.ha_participado_en_las_jornadas
                            val e=y.ha_usado_aplicaciones
                            val f=y.municipio
                            val g=y.sabe_que_significa_la_sigla
                            val h=y.tiene_residuos_postconsumo
                            val i=y.tipo_de_empresa
                            val j=y.utilizaria_una_app_para_raee
                            System.out.println(a.toString()+"----"+b)
                            var item = MunicipioItem(a,b,c,d,e,f,g,h,i,j)
                            items_blog!!.add(item)

                            counter = counter+1
                        }

                    }
                    v(items_blog!!)
                } else {
                    System.out.println("RETROFIT_ERROR")
                }
            }
        }
    }
    fun v(items_blog: ArrayList<MunicipioItem>){
        //Vertical recycler
        adaptador_V = AdapterRecyclerVertical(items_blog)
        layoutmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        main_item_H?.layoutManager = layoutmanager
        main_item_H?.adapter = adaptador_V

    }
}