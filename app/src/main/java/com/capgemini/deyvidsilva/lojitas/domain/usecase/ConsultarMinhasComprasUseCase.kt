package com.capgemini.deyvidsilva.lojitas.domain.usecase

import com.capgemini.deyvidsilva.lojitas.data.repository.LojitasRepository
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pedido

class ConsultarMinhasComprasUseCase(
    private val repository: LojitasRepository
) {
    operator fun invoke(): List<Pedido> {
        return repository.obterMeusPedidos();
    }
}