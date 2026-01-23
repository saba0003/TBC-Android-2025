package com.example.tbc_android_2025.presentation.screen.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.component.CollectSideEffect
import com.example.tbc_android_2025.presentation.model.OrderModel
import com.example.tbc_android_2025.presentation.screen.orders.OrdersContract.Event
import com.example.tbc_android_2025.presentation.screen.orders.OrdersContract.State

private const val SHOW_BACKGROUND = true

@Composable
fun OrdersScreen(viewModel: OrdersViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    OrdersScreenContent(
        state = state,
        onFilterSelected = { viewModel.onEvent(event = Event.OnFilterChanged(filter = it)) },
        onRefresh = { viewModel.onEvent(event = Event.OnFetchOrders) },
        onDetailsClicked = { viewModel.onEvent(event = Event.OnDetailsClicked) }
    )

    CollectSideEffect(flow = viewModel.sideEffect) {
        when (it) {
            OrdersContract.SideEffect.NavigateToDetails -> Unit
            is OrdersContract.SideEffect.ShowError -> Unit
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun OrdersScreenContent(
    state: State,
    onFilterSelected: (OrderModel.Status) -> Unit,
    onRefresh: () -> Unit,
    onDetailsClicked: () -> Unit
) {
    val filteredOrders = remember(state.orders, state.selectedFilter) {
        state.orders.filter { it.status == state.selectedFilter }
    }

    PullToRefreshBox(
        isRefreshing = state.isLoading, onRefresh = onRefresh, modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = Strings.my_orders),
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
                    OrderModel.Status.entries.forEach {
                        FilterTab(
                            text = stringResource(id = it.stringResId),
                            isSelected = it == state.selectedFilter,
                            onClick = { onFilterSelected(it) }
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
                    text = stringResource(id = Strings.order_number, order.orderNumber),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = order.date, color = Color.Gray, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            OrderInfoRow(
                label = stringResource(id = Strings.tracking_number),
                value = order.trackingNumber
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OrderInfoRow(
                    label = stringResource(id = Strings.quantity),
                    value = order.quantity.toString()
                )
                OrderInfoRow(
                    label = stringResource(id = Strings.subtotal),
                    value = order.subtotal.toString()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (order.status) {
                        OrderModel.Status.PENDING -> stringResource(id = Strings.pending).uppercase()
                        OrderModel.Status.DELIVERED -> stringResource(id = Strings.delivered).uppercase()
                        OrderModel.Status.CANCELED -> stringResource(id = Strings.canceled).uppercase()
                    },
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
                    Text(text = stringResource(id = Strings.details), color = Color.Black)
                }
            }
        }
    }
}

@Composable
private fun OrderInfoRow(label: String, value: String) {
    Row {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
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
            date = "13/05/2025",
            trackingNumber = "IK287368838",
            quantity = 2,
            subtotal = 110,
            status = OrderModel.Status.PENDING
        ),
        OrderModel(
            id = 2,
            orderNumber = "1525",
            date = "14/05/2025",
            trackingNumber = "IK287368839",
            quantity = 1,
            subtotal = 50,
            status = OrderModel.Status.PENDING
        ),
        OrderModel(
            id = 3,
            orderNumber = "1526",
            date = "15/05/2025",
            trackingNumber = "IK287368840",
            quantity = 5,
            subtotal = 450,
            status = OrderModel.Status.PENDING
        )
    )

    OrdersScreenContent(
        state = State(orders = mockOrders),
        onFilterSelected = {},
        onRefresh = {},
        onDetailsClicked = {}
    )
}
