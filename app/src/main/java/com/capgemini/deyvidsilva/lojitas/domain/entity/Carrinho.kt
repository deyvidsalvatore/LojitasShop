package com.capgemini.deyvidsilva.lojitas.domain.entity

data class Carrinho(
    val itens: List<CarrinhoItem>,
    val valorTotal: Double
)
