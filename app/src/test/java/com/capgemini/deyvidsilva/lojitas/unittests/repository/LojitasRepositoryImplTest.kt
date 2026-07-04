package com.capgemini.deyvidsilva.lojitas.unittests.repository

import com.capgemini.deyvidsilva.lojitas.data.mock.PedidosMock
import com.capgemini.deyvidsilva.lojitas.data.repository.impl.LojitasRepositoryImpl
import com.capgemini.deyvidsilva.lojitas.domain.entity.Endereco
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pagamento
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.enums.StatusPedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.enums.TipoPagamento
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LojitasRepositoryImplTest {

    private lateinit var repository: LojitasRepositoryImpl

    @Before
    fun setUp() {
        repository = LojitasRepositoryImpl()
        PedidosMock.pedidosRealizados.clear()
    }

    @Test
    fun `obterTodosProdutos deve retornar a lista completa de mock`() {
        val produtos = repository.obterTodosProdutos();
        Assert.assertEquals(40, produtos.size)
    }

    @Test
    fun `buscarProdutosPorNome deve retornar produtos que contenham o termo ignorando case`() {
        val resultadoNome = repository.buscarProdutosPorNome("monitor")
        Assert.assertEquals(4, resultadoNome.size)
        Assert.assertEquals("Monitor Gamer 24\"", resultadoNome.first().nome)

        val resultadoDescricao = repository.buscarProdutosPorNome("armazenamento")
        Assert.assertEquals(2, resultadoDescricao.size)
        Assert.assertEquals("Pen Drive 128GB", resultadoDescricao.first().nome)
    }

    @Test
    fun `buscarProdutosPorNome deve retornar lista vazia se nao encontrar nada`() {
        val resultado = repository.buscarProdutosPorNome("Nada")
        Assert.assertTrue(resultado.isEmpty())
    }

    @Test
    fun `obterProdutoPorId deve retornar o produto correto se o ID existir`() {
        val produto = repository.obterProdutoPorId("ELE001")
        Assert.assertNotNull(produto)
        Assert.assertEquals("Headset Bluetooth", produto?.nome);
    }

    @Test
    fun `obterProdutoPorId deve retorna null se o ID nao existir`() {
        val produto = repository.obterProdutoPorId("NAO_EXISTO");
        Assert.assertNull(produto);
    }

    @Test
    fun `salvarPedido deve adicionar o pedido a lista de memoria`() {
        val endereooMock =
            Endereco("João", "00000-000", "Rua A", "123", "Bairro X", "Cidade Y", "PE")
        val pagamentoMock = Pagamento(TipoPagamento.PIX)
        val pedido =
            Pedido("PED1", emptyList(), 100.0, endereooMock, pagamentoMock, StatusPedido.PAGO)

        repository.salvarPedido(pedido)
        val meuPedidos = repository.obterMeusPedidos();

        Assert.assertEquals(1, meuPedidos.size)
        Assert.assertEquals("PED1", meuPedidos.first().id)
    }

}