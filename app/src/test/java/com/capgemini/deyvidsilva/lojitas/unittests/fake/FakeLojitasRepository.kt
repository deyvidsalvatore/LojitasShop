package com.capgemini.deyvidsilva.lojitas.unittests.fake

import com.capgemini.deyvidsilva.lojitas.data.repository.LojitasRepository
import com.capgemini.deyvidsilva.lojitas.domain.entity.Categoria
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

class FakeLojitasRepository : LojitasRepository {
    val produtos = listOf(
        Produto("1", "Teclado", "Mecânico", Categoria("1", "Info"), 200.0, 0, 10),
        Produto("2", "Mouse", "Sem fio", Categoria("1", "Info"), 150.0, 0, 5)
    )
    val pedidos = mutableListOf<Pedido>()

    override fun obterTodosProdutos(): List<Produto> = produtos

    override fun buscarProdutosPorNome(query: String): List<Produto> {
        return produtos.filter { it.nome.contains(query, ignoreCase = true) }
    }

    override fun obterProdutoPorId(id: String): Produto? {
        return produtos.find { it.id == id }
    }

    override fun salvarPedido(pedido: Pedido) {
        pedidos.add(pedido)
    }

    override fun obterMeusPedidos(): List<Pedido> = pedidos
}