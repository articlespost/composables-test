package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat


data class CartItem(
    val itemDescription: String?,
    val sglUnit: String?,
    val itemQtd: Int?,
    val unitValue: Double?,
    val totalValue: Double?
)


@Composable
fun ListItems(cartItems: List<CartItem> = emptyList()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(300.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color(0xFFE3E3E3)),
    ) {



        Column {

            // TÍTULOS DAS COLUNAS
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ColumnTitle(title = "Produto", modifier = Modifier.weight(2f))
                ColumnTitle(title = "UR", modifier = Modifier.weight(1f))
                ColumnTitle(title = "Qtd.", modifier = Modifier.weight(1f))
                ColumnTitle(title = "Preço", modifier = Modifier.weight(1f))
                ColumnTitle(title = "Total", modifier = Modifier.weight(1f))
            }

            // DIVISOR
            HorizontalDivider(
                modifier = Modifier.padding(
                    top = 8.dp,
                    end = 8.dp,
                    start = 8.dp,
                    bottom = 8.dp
                ),
                thickness = 1.dp,
                color = Color(0xFF929090),
            )

            // LISTA DE ITENS
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
            ) {
                if (cartItems.isNotEmpty()) LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp)
                ) {
                    items(cartItems.size) { index ->
                        val item = cartItems[index]
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ColumnContent(
                                content = item.itemDescription!!,
                                modifier = Modifier
                                    .weight(2f)
                                    .testTag("product_name_$index")
                            )
                            ColumnContent(
                                content = item.sglUnit!!,
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("unit_$index")
                            )
                            ColumnContent(
                                content = item.itemQtd.toString(),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("quantity_$index")
                            )
                            ColumnContent(
                                content = formatCurrency(item.unitValue!!),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("price_$index")
                            )
                            ColumnContent(
                                content = formatCurrency(item.totalValue!!),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("total_$index")
                            )

                        }
                    }
                } else Text(
                    modifier = Modifier.align(Alignment.Center).testTag("empty_cart"),
                    text = "Não há produtos ou abastecimentos.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                )

            }


        }
    }
}

@Composable
private fun ColumnTitle(title: String, modifier: Modifier = Modifier) {
    BasicText(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp)
    )
}

@Composable
private fun ColumnContent(content: String, modifier: Modifier = Modifier) {
    BasicText(
        text = content,
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp)
    )
}

private fun formatCurrency(amount: Double): String {
    val decimalFormat = DecimalFormat("#,##0.00")
    return decimalFormat.format(amount).replace(".", ",")
}
