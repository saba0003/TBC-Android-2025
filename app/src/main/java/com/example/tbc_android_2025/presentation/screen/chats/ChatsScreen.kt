package com.example.tbc_android_2025.presentation.screen.chats

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tbc_android_2025.presentation.component.CollectSideEffect
import com.example.tbc_android_2025.presentation.extension.loadImage
import com.example.tbc_android_2025.presentation.model.ChatModel
import com.example.tbc_android_2025.presentation.screen.chats.ChatsContract.Event
import com.example.tbc_android_2025.presentation.screen.chats.ChatsContract.SideEffect
import com.example.tbc_android_2025.presentation.screen.chats.ChatsContract.State

// UI Constants
val BackgroundDark = Color(0xFF1F2C34)
val SurfaceDark = Color(0xFF2A3942)
val AccentGreen = Color(0xFF4ADE80)
val TextGrey = Color(0xFF8696A0)

@Composable
fun ChatsScreen(viewModel: ChatsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ChatsScreenContent(
        state = state,
        onRefresh = { viewModel.onEvent(Event.OnFetchChats) },
        // This only gets called when the user actually triggers a search
        onTriggerSearch = { query -> viewModel.onEvent(Event.OnSearch(query)) }
    )

    CollectSideEffect(flow = viewModel.sideEffect) {
        when (it) {
            is SideEffect.ShowError -> { /* Handle Error UI */ }
        }
    }
}

@Composable
private fun ChatsScreenContent(
    state: State,
    onRefresh: () -> Unit,
    onTriggerSearch: (String) -> Unit
) {
    Scaffold(
        containerColor = BackgroundDark,
        bottomBar = { CustomBottomBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            SearchArea(
                onSearchClick = onTriggerSearch
            )

            PullToRefreshBox(
                isRefreshing = state.isLoading,
                onRefresh = onRefresh,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.filteredChats, key = { it.id }) { chat ->
                        ChatItem(chat = chat)
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 85.dp),
                            thickness = 0.5.dp,
                            color = Color.White.copy(alpha = 0.05f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchArea(
    onSearchClick: (String) -> Unit
) {
    // Local state to keep track of typing without triggering ViewModel updates
    var localQuery by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .background(SurfaceDark, RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Mocking the Google "G" Icon
            Text(
                text = "G",
                color = TextGrey,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(contentAlignment = Alignment.CenterStart) {
                if (localQuery.isEmpty()) {
                    Text("Search", color = TextGrey, fontSize = 16.sp)
                }
                BasicTextField(
                    value = localQuery,
                    onValueChange = { localQuery = it },
                    textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                    cursorBrush = SolidColor(AccentGreen),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // The Search Trigger Button
        IconButton(
            onClick = { onSearchClick(localQuery) },
            modifier = Modifier
                .size(56.dp)
                .background(AccentGreen, RoundedCornerShape(16.dp))
        ) {
            Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = "Trigger Search",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun ChatItem(chat: ChatModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .background(getAvatarColor(chat.id)),
            contentAlignment = Alignment.Center
        ) {
            if (chat.image != null) {
                AndroidView(
                    factory = { context ->
                        ImageView(context).apply {
                            scaleType = ImageView.ScaleType.CENTER_CROP
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                    update = { imageView ->
                        imageView.loadImage(chat.image)
                    }
                )
            } else {
                Text(getAvatarEmoji(chat.id), fontSize = 24.sp)
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.owner,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                when {
                    chat.isTyping -> {
                        Text(chat.lastMessage, color = TextGrey, fontSize = 14.sp)
                    }
                    chat.lastMessageType == "voice" -> {
                        Icon(Icons.Default.Mic, null, Modifier.size(16.dp), tint = TextGrey)
                        Spacer(Modifier.width(6.dp))
                        Text("Sent a voice message", color = TextGrey, fontSize = 14.sp)
                    }
                    chat.lastMessageType == "file" -> {
                        Icon(Icons.Default.AttachFile, null, Modifier.size(16.dp), tint = TextGrey)
                        Spacer(Modifier.width(6.dp))
                        Text("Sent an attachment", color = TextGrey, fontSize = 14.sp)
                    }
                    else -> {
                        Text(
                            text = chat.lastMessage,
                            color = TextGrey,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(chat.lastActive, color = TextGrey, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))

            if (chat.unreadMessages > 0) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(AccentGreen, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chat.unreadMessages.toString(),
                        color = BackgroundDark,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else if (chat.isTyping) {
                Text("..", color = AccentGreen, fontWeight = FontWeight.Black, fontSize = 18.sp)
            }
        }
    }
}

@Composable
private fun CustomBottomBar() {
    Surface(
        color = SurfaceDark,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(vertical = 20.dp, horizontal = 45.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Rounded.FavoriteBorder, null, tint = TextGrey, modifier = Modifier.size(26.dp))
            Icon(Icons.Rounded.Home, null, tint = TextGrey, modifier = Modifier.size(28.dp))
            Icon(Icons.Rounded.ChatBubble, null, tint = AccentGreen, modifier = Modifier.size(26.dp))
        }
    }
}

private fun getAvatarColor(id: Int) = when (id % 3) {
    0 -> Color(0xFFFFCA28)
    1 -> Color(0xFF4ADE80)
    else -> Color(0xFFF87171)
}

private fun getAvatarEmoji(id: Int) = when (id % 4) {
    0 -> "👩🏾"
    1 -> "👨🏻"
    2 -> "👧🏻"
    else -> "👴🏼"
}

@Preview
@Composable
private fun ChatsScreenPreview() {
    val mockChats = listOf(
        ChatModel(1, null, "Alice Smith", "Great. I will have a look", "4:20 PM", 3, false, "text"),
        ChatModel(2, null, "Alice Smith", "Great. I will have a look", "4:20 PM", 0, true, "text"),
        ChatModel(3, null, "Alice Smith", "", "4:20 PM", 0, false, "voice"),
        ChatModel(4, null, "Alice Smith", "", "4:20 PM", 0, false, "file")
    )
    MaterialTheme {
        ChatsScreenContent(
            state = State(chats = mockChats),
            onRefresh = {},
            onTriggerSearch = {}
        )
    }
}
