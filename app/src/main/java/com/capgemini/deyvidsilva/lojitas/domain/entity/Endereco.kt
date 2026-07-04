package com.capgemini.deyvidsilva.lojitas.domain.entity

data class Endereco(
    val nomeDestinatario: String,
    val cep: String,
    val rua: String,
    val numero: String,
    val bairro: String,
    val cidade: String,
    val estado: String
)
