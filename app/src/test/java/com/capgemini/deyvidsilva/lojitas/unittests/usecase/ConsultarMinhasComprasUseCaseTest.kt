package com.capgemini.deyvidsilva.lojitas.unittests.usecase

import com.capgemini.deyvidsilva.lojitas.domain.entity.Endereco
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pagamento
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.enums.StatusPedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.enums.TipoPagamento
import com.capgemini.deyvidsilva.lojitas.domain.usecase.ConsultarMinhasComprasUseCase
import com.capgemini.deyvidsilva.lojitas.unittests.fake.FakeLojitasRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ConsultarMinhasComprasUseCaseTest {

    private lateinit var fakeRepository: FakeLojitasRepository
    private lateinit var useCase: ConsultarMinhasComprasUseCase

    @Before
    fun setUp() {
        fakeRepository = FakeLojitasRepository()
        useCase = ConsultarMinhasComprasUseCase(fakeRepository)
    }

    @Test
    fun `deve retornar a lista de pedidos do repositorio`() {
        val enderecoMock = Endereco("João", "00000-000", "Rua A", "12", "Bairro", "Cidade", "PE")
        val pagamentoMock = Pagamento(TipoPagamento.CARTAO)
        val pedido = Pedido("ped_teste", emptyList(), 200.0, enderecoMock, pagamentoMock,
            StatusPedido.PAGO)

        fakeRepository.salvarPedido(pedido)

        val resultado = useCase()

        assertEquals(1, resultado.size)
        assertEquals("ped_teste", resultado.first().id)
    }
}