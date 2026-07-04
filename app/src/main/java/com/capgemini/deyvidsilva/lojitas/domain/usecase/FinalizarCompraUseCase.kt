package com.capgemini.deyvidsilva.lojitas.domain.usecase

import com.capgemini.deyvidsilva.lojitas.data.repository.LojitasRepository
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

class FinalizarCompraUseCase(
    private val repository: LojitasRepository
) {
    operator fun invoke(pedido: Pedido) {
        repository.salvarPedido(pedido);
    }
}