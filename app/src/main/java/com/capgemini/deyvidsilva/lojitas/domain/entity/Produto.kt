package com.capgemini.deyvidsilva.lojitas.domain.entity

data class Produto(
    val id: String,
    val nome: String,
    val descricao: String,
    val categoria: Categoria,
    val preco: Double,
    val imagemProduto: Int,
    val estoque: Int
)
