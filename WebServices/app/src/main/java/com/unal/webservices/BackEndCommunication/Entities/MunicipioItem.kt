package com.unal.webservices.BackEndCommunication.Entities

data class MunicipioItem(
    val como_se_enteraron_de_esta: String,
    val donde_entrega_los_residuos: String,
    val entidad: String,
    val ha_participado_en_las_jornadas: String,
    val ha_usado_aplicaciones: String,
    val municipio: String,
    val sabe_que_significa_la_sigla: String,
    val tiene_residuos_postconsumo: String,
    val tipo_de_empresa: String,
    val utilizaria_una_app_para_raee: String
)