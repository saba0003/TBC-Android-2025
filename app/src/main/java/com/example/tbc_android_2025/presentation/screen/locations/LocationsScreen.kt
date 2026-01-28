package com.example.tbc_android_2025.presentation.screen.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.NightsStay
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material.icons.rounded.Whatshot
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.component.AppBottomNavBar
import com.example.tbc_android_2025.presentation.component.AppImage
import com.example.tbc_android_2025.presentation.component.AppRatingBar
import com.example.tbc_android_2025.presentation.extension.CollectSideEffect
import com.example.tbc_android_2025.presentation.model.LocationModel
import com.example.tbc_android_2025.presentation.screen.NavBarIcons
import com.example.tbc_android_2025.presentation.screen.locations.LocationsContract.SideEffect
import com.example.tbc_android_2025.presentation.screen.locations.LocationsContract.State
import kotlin.math.absoluteValue

private const val SHOW_BACKGROUND = true

@Composable
fun LocationsScreen(viewModel: LocationsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.sideEffect.CollectSideEffect {
        when (it) {
            is SideEffect.ShowError -> { /* Handle Error UI */
            }
        }
    }

    LocationsScreenContent(state = state)
}

@Composable
private fun LocationsScreenContent(state: State) {
    var selectedNav by remember { mutableStateOf(NavBarIcons.HOME) }
    val pagerState = rememberPagerState(pageCount = { state.locations.size })

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1D262B)) // Dark background from image
            .systemBarsPadding(),
        bottomBar = {
            AppBottomNavBar(
                selectedIcon = selectedNav,
                onIconSelected = { selectedNav = it }
            )
        },
        containerColor = Color(0xFF1D262B)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
// Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = Strings.statistics),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                // Day/Night Theme Switcher (Non-functional for now)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.WbSunny,
                        contentDescription = stringResource(id = Strings.light_mode),
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(enabled = false) { /* TODO */ }
                    )
                    Icon(
                        imageVector = Icons.Rounded.NightsStay,
                        contentDescription = stringResource(id = Strings.dark_mode),
                        tint = Color.White.copy(alpha = 0.5f), // Slightly dimmed to show it's "inactive"
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(enabled = false) { /* TODO */ }
                    )
                }
            }

            // Pager with Scaling Effect
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Flexible height
                contentPadding = PaddingValues(horizontal = 48.dp),
                pageSpacing = 20.dp
            ) { page ->
                val pageOffset = (
                        (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                        ).absoluteValue

                LocationItem(
                    locationItem = state.locations[page],
                    modifier = Modifier.graphicsLayer {
                        val scale = lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleX = 1f
                        scaleY = scale
                    }
                )
            }

            // Spacer to lift pager slightly from navbar
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun LocationItem(
    locationItem: LocationModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(40.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AppImage(url = locationItem.photo, modifier = Modifier.fillMaxSize())

            // Bottom Gradient Scrim for text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                            startY = 400f
                        )
                    )
            )

            // Content Overlay
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Row: Location & Info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.LocationOn,
                            null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            locationItem.location,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.Whatshot,
                            null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            locationItem.number,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Bottom section: Title, Price and Rating
                Column {
                    Text(
                        text = locationItem.title,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 38.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$${locationItem.price}",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        AppRatingBar(rating = locationItem.stars)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun LocationsScreenPreview() = LocationsScreenContent(
    state = State(
        locations = listOf(
            element = LocationModel(
                id = 1,
                title = "Natural Walk To The Top",
                location = "Barcelona",
                number = "2500",
                photo = "https://picsum.photos/seed/tour1/800/1200",
                price = 120,
                stars = 4
            )
        )
    )
)
