package com.capgemini.deyvidsilva.lojitas.data.repository

import com.capgemini.deyvidsilva.lojitas.domain.entity.Pedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

interface LojitasRepository {
    fun obterTodosProdutos(): List<Produto>
    fun buscarProdutosPorNome(query: String): List<Produto>
    fun obterProdutoPorId(id: String): Produto?
    fun salvarPedido(pedido: Pedido)
    fun obterMeusPedidos(): List<Pedido>
}