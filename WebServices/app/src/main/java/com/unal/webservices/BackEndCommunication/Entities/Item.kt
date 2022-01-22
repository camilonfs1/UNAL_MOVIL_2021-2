package com.unal.webservices.BackEndCommunication.Entities

import java.io.Serializable

data class Item (
    val municipio: String,
    val tipo_de_empresa: String,
    val entidad: String,
    val tiene_residuos_postconsumo: String,
    val donde_entrega_los_residuos: String,
    val sabe_que_significa_la_sigla: String,
    val ha_participado_en_las_jornadas: String,
    val como_se_enteraron_de_esta: String,
    val ha_usado_aplicaciones: String,
    val utilizaria_una_app_para_raee: String
): Serializable
