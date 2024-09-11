package com.example.testscomposables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.CartItem
import com.example.myapplication.ui.ListItems
import com.example.testscomposables.ui.theme.TestsComposablesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestsComposablesTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val listItems = remember { listCartItems }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Lista de Produtos",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.W700
            )
        )
        ListItems(cartItems = listItems)
    }
}

private val listCartItems = listOf(
    CartItem(
        itemDescription = "PRODUTO 1",
        sglUnit = "UN",
        itemQtd = 1,
        unitValue = 10.0,
        totalValue = 10.0
    ),
    CartItem(
        itemDescription = "PRODUTO 2",
        sglUnit = "UN",
        itemQtd = 1,
        unitValue = 10.0,
        totalValue = 10.0
    )
)