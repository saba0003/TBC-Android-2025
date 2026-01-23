package com.example.tbc_android_2025.presentation.screen.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tbc_android_2025.presentation.model.OrderModel
import com.example.tbc_android_2025.presentation.screen.orders.OrdersContract.*

private const val SHOW_BACKGROUND = true

@Composable
fun OrdersScreen(viewModel: OrdersViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    // We lift the filter state here so the Content remains stateless
    var selectedFilter by remember { mutableStateOf(OrderModel.Status.PENDING) }

    OrdersScreenContent(
        state = state,
        selectedFilter = selectedFilter,
        onFilterSelected = { selectedFilter = it },
        onRefresh = { viewModel.onEvent(event = Event.OnFetchOrders) },
        onDetailsClicked = { viewModel.onEvent(event = Event.OnDetailsClicked) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OrdersScreenContent(
    state: State,
    selectedFilter: OrderModel.Status,
    onFilterSelected: (OrderModel.Status) -> Unit,
    onRefresh: () -> Unit,
    onDetailsClicked: () -> Unit
) {
    val filteredOrders = remember(state.orders, selectedFilter) {
        state.orders.filter { it.status == selectedFilter }
    }

    PullToRefreshBox(
        isRefreshing = state.isLoading, onRefresh = onRefresh, modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "My Orders",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Outlined.Notifications, contentDescription = null)
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFF9F9F9))
            ) {
                // Filter Section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OrderModel.Status.entries.forEach { status ->
                        val isSelected = status == selectedFilter
                        FilterTab(
                            text = status.name.lowercase().replaceFirstChar { it.uppercase() },
                            isSelected = isSelected,
                            onClick = { onFilterSelected(status) }
                        )
                    }
                }

                // List Section
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredOrders) { order ->
                        OrderCard(order = order, onDetailsClick = onDetailsClicked)
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterTab(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF4F4F4F) else Color.Transparent,
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = null,
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun OrderCard(order: OrderModel, onDetailsClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Order #${order.orderNumber}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = order.date, color = Color.Gray, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            OrderInfoRow("Tracking number:", order.trackingNumber, isValueBold = true)
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    OrderInfoRow("Quantity:", order.quantity.toString())
                }
                Box(modifier = Modifier.weight(1f)) {
                    OrderInfoRow("Subtotal:", "$${order.subtotal}", isValueBold = true)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = order.status.name,
                    color = when (order.status) {
                        OrderModel.Status.PENDING -> Color(0xFFF2994A)
                        OrderModel.Status.DELIVERED -> Color(0xFF27AE60)
                        OrderModel.Status.CANCELED -> Color(0xFFEB5757)
                    },
                    fontWeight = FontWeight.Bold
                )

                OutlinedButton(
                    onClick = onDetailsClick,
                    shape = RoundedCornerShape(20.dp),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true)
                ) {
                    Text("Details", color = Color.Black)
                }
            }
        }
    }
}

@Composable
private fun OrderInfoRow(label: String, value: String, isValueBold: Boolean = false) {
    Row {
        Text(text = "$label ", color = Color.Gray, fontSize = 14.sp)
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = if (isValueBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun OrdersScreenPreview() {
    val mockOrders = listOf(
        OrderModel(
            id = 1,
            orderNumber = "1524",
            date = "13/05/2021",
            trackingNumber = "IK287368838",
            quantity = 2,
            subtotal = 110,
            status = OrderModel.Status.PENDING
        ),
        OrderModel(
            id = 2,
            orderNumber = "1525",
            date = "14/05/2021",
            trackingNumber = "IK287368839",
            quantity = 1,
            subtotal = 50,
            status = OrderModel.Status.PENDING
        ),
        OrderModel(
            id = 3,
            orderNumber = "1526",
            date = "15/05/2021",
            trackingNumber = "IK287368840",
            quantity = 5,
            subtotal = 450,
            status = OrderModel.Status.DELIVERED
        )
    )

    OrdersScreenContent(
        state = State(orders = mockOrders, isLoading = false),
        selectedFilter = OrderModel.Status.PENDING,
        onFilterSelected = {},
        onRefresh = {},
        onDetailsClicked = {}
    )
}
