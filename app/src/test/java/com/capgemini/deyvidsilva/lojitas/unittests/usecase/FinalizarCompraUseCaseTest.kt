package com.capgemini.deyvidsilva.lojitas.unittests.usecase

import com.capgemini.deyvidsilva.lojitas.domain.entity.Endereco
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pagamento
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.enums.StatusPedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.enums.TipoPagamento
import com.capgemini.deyvidsilva.lojitas.domain.usecase.FinalizarCompraUseCase
import com.capgemini.deyvidsilva.lojitas.unittests.fake.FakeLojitasRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FinalizarCompraUseCaseTest {

    private lateinit var fakeRepository: FakeLojitasRepository
    private lateinit var useCase: FinalizarCompraUseCase

    @Before
    fun setUp() {
        fakeRepository = FakeLojitasRepository()
        useCase = FinalizarCompraUseCase(fakeRepository)
    }

    @Test
    fun `deve salvar o pedido no repositorio`() {
        val enderecoMock = Endereco("João", "00000-000", "Rua A", "12", "Bairro", "Cidade", "RN")
        val pagamentoMock = Pagamento(TipoPagamento.CARTAO)
        val pedido = Pedido("ped_teste", emptyList(), 200.0, enderecoMock, pagamentoMock, StatusPedido.PAGO)

        useCase(pedido)

        assertEquals(1, fakeRepository.pedidos.size)
        assertEquals("ped_teste", fakeRepository.pedidos.first().id)
    }
}