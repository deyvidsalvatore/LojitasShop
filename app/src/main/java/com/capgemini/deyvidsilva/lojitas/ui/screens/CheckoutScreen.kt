package com.capgemini.deyvidsilva.lojitas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.capgemini.deyvidsilva.lojitas.data.repository.impl.LojitasRepositoryImpl
import com.capgemini.deyvidsilva.lojitas.domain.entity.CarrinhoItem
import com.capgemini.deyvidsilva.lojitas.domain.entity.Endereco
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pagamento
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.enums.StatusPedido
import com.capgemini.deyvidsilva.lojitas.domain.entity.enums.TipoPagamento
import com.capgemini.deyvidsilva.lojitas.domain.usecase.FinalizarCompraUseCase
import com.capgemini.deyvidsilva.lojitas.navigation.Routes
import com.capgemini.deyvidsilva.lojitas.ui.components.ButtonComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.DialogConfirmationComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.StepIndicatorComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.TopBarComponent
import kotlinx.coroutines.delay
import java.util.UUID
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun CheckoutScreen(
    navController: NavController,
    itensCarrinho: List<CarrinhoItem>,
    onClearCart: () -> Unit
) {
    val repository = remember { LojitasRepositoryImpl() }
    val finalizarCompraUseCase = remember { FinalizarCompraUseCase(repository) }

    var currentStep by remember { mutableStateOf(1) }
    var showSuccessModal by remember { mutableStateOf(false) }

    var nomeDestinatario by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var rua by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }

    LaunchedEffect(cep) {
        val cepLimpo = cep.replace("-", "")

        if (cepLimpo.length == 8) {
            delay(1000.milliseconds)

            rua = "Avenida Boa Viagem"
            bairro = "Boa Viagem"
            cidade = "Recife"
            estado = "PE"
        }
    }

    var tipoPagamento by remember { mutableStateOf(TipoPagamento.CARTAO) }
    var numeroCartao by remember { mutableStateOf("") }
    var nomeTitular by remember { mutableStateOf("") }

    val valorTotal = itensCarrinho.sumOf { it.produto.preco * it.quantidade }

    if (showSuccessModal) {
        DialogConfirmationComponent(
            onConfirm = {
                showSuccessModal = false
                onClearCart()
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopBarComponent(
                title = "Checkout",
                canNavigateBack = true,
                onNavigateBack = {
                    if (currentStep > 1) currentStep -= 1 else navController.popBackStack()
                }
            )
        },
        bottomBar = {
            Box(modifier = Modifier.padding(16.dp)) {
                ButtonComponent(
                    text = when (currentStep) {
                        1 -> "Ir para Pagamento"
                        2 -> "Revisar Pedido"
                        else -> "Confirmar e Pagar"
                    },
                    enabled = when (currentStep) {
                        1 -> nomeDestinatario.isNotBlank() && rua.isNotBlank() && numero.isNotBlank()
                        2 -> tipoPagamento == TipoPagamento.PIX || numeroCartao.isNotBlank()
                        else -> true
                    },
                    onClick = {
                        if (currentStep < 3) {
                            currentStep += 1
                        } else {
                            val endereco = Endereco(nomeDestinatario, cep, rua, numero, bairro, cidade, estado)
                            val pagamento = Pagamento(tipoPagamento, numeroCartao, nomeTitular)
                            val pedido = Pedido(
                                id = UUID.randomUUID().toString().take(8),
                                itens = itensCarrinho.toList(),
                                valorTotal = valorTotal,
                                enderecoEntrega = endereco,
                                formaPagamento = pagamento,
                                status = StatusPedido.PAGO
                            )
                            finalizarCompraUseCase(pedido)
                            showSuccessModal = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            StepIndicatorComponent(currentStep = currentStep)

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                when (currentStep) {
                    1 -> {
                        Text("Dados de Entrega", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(value = nomeDestinatario, onValueChange = { nomeDestinatario = it }, label = { Text("Nome do Destinatário") }, modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(value = cep, onValueChange = { cep = it }, label = { Text("CEP") }, modifier = Modifier.weight(1f))
                            OutlinedTextField(value = estado, onValueChange = { estado = it }, label = { Text("Estado") }, modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(value = rua, onValueChange = { rua = it }, label = { Text("Rua") }, modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(value = numero, onValueChange = { numero = it }, label = { Text("Número") }, modifier = Modifier.weight(1f))
                            OutlinedTextField(value = bairro, onValueChange = { bairro = it }, label = { Text("Bairro") }, modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(value = cidade, onValueChange = { cidade = it }, label = { Text("Cidade") }, modifier = Modifier.fillMaxWidth())
                    }
                    2 -> {
                        Text("Forma de Pagamento", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = tipoPagamento == TipoPagamento.CARTAO, onClick = { tipoPagamento = TipoPagamento.CARTAO })
                            Text("Cartão de Crédito")
                            Spacer(modifier = Modifier.width(16.dp))
                            RadioButton(selected = tipoPagamento == TipoPagamento.PIX, onClick = { tipoPagamento = TipoPagamento.PIX })
                            Text("PIX")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        if (tipoPagamento == TipoPagamento.CARTAO) {
                            OutlinedTextField(value = numeroCartao, onValueChange = { numeroCartao = it }, label = { Text("Número do Cartão Fictício") }, modifier = Modifier.fillMaxWidth())
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(value = nomeTitular, onValueChange = { nomeTitular = it }, label = { Text("Nome do Titular") }, modifier = Modifier.fillMaxWidth())
                        } else {
                            Box(modifier = Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) {
                                Text("Código PIX gerado: \n00020101021126580014br.gov.bcb.pix...", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    3 -> {
                        Text("Resumo do Pedido", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))

                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Endereço:", fontWeight = FontWeight.Bold)
                                Text("$rua, $numero - $bairro")
                                Text("$cidade - $estado")
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Pagamento:", fontWeight = FontWeight.Bold)
                                Text(if (tipoPagamento == TipoPagamento.CARTAO) "Cartão de Crédito" else "PIX")
                                Spacer(modifier = Modifier.height(16.dp))
                                HorizontalDivider()
                                Spacer(modifier = Modifier.height(16.dp))
                                Text("Total a Pagar:", fontWeight = FontWeight.Bold)
                                Text("R$ ${String.format("%.2f", valorTotal)}", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}