package com.capgemini.deyvidsilva.lojitas.data.repository.impl

import com.capgemini.deyvidsilva.lojitas.data.mock.PedidosMock
import com.capgemini.deyvidsilva.lojitas.data.mock.ProdutosMock
import com.capgemini.deyvidsilva.lojitas.data.repository.LojitasRepository
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

class LojitasRepositoryImpl : LojitasRepository {

    override fun obterTodosProdutos(): List<Produto> {
        return ProdutosMock.listaProdutos
    }

    override fun buscarProdutosPorNome(query: String): List<Produto> {
        return ProdutosMock.listaProdutos.filter { produto ->
            produto.nome.contains(query, ignoreCase = true) ||
                    produto.descricao.contains(query, ignoreCase = true)
        }
    }

    override fun obterProdutoPorId(id: String): Produto? {
        return ProdutosMock.listaProdutos.find { it.id == id }
    }

    override fun salvarPedido(pedido: Pedido) {
        PedidosMock.pedidosRealizados.add(pedido)
    }

    override fun obterMeusPedidos(): List<Pedido> {
        return PedidosMock.pedidosRealizados.toList()
    }
}