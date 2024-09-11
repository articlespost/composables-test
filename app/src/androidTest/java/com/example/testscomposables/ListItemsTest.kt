package com.example.testscomposables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.myapplication.ui.CartItem
import com.example.myapplication.ui.ListItems
import org.junit.Rule
import org.junit.Test

class ListItemsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun listItemsTest() {
        val listItems = listOf(
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

        composeTestRule.setContent {
            ListItems(cartItems = listItems)
        }

        listItems.forEachIndexed() { index, cartItem ->
            composeTestRule.onNodeWithTag("product_name_$index").assertIsDisplayed()
            composeTestRule.onNodeWithTag("unit_$index").assertIsDisplayed()
            composeTestRule.onNodeWithTag("quantity_$index").assertIsDisplayed()
            composeTestRule.onNodeWithTag("price_$index").assertIsDisplayed()
            composeTestRule.onNodeWithTag("total_$index").assertIsDisplayed()
        }

    }


    @Test
    fun listCartItemsEmptyTest() {
        val listItems = listOf<CartItem>()

        composeTestRule.setContent {
            ListItems(cartItems = listItems)
        }

        composeTestRule.onNodeWithTag("empty_cart").assertIsDisplayed()
    }

}