package com.capgemini.deyvidsilva.lojitas.domain.entity

import com.capgemini.deyvidsilva.lojitas.domain.entity.enums.StatusPedido

data class Pedido(
    val id: String,
    val itens: List<CarrinhoItem>,
    val valorTotal: Double,
    val enderecoEntrega: Endereco,
    val formaPagamento: Pagamento,
    val status: StatusPedido
)
