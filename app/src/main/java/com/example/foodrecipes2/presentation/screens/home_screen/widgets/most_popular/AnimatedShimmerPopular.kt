package com.example.foodrecipes2.presentation.screens.home_screen.widgets.most_popular

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedShimmerPopoular() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )
    Box(modifier = Modifier.fillMaxWidth()){
        ShimmerGridItem(brush = brush)
    }

}

@Composable
fun AnimatedImageShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerGridImageItem(brush = brush)
}

@Composable
fun ShimmerGridItem(brush: Brush) {
    Column(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

            Column( ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .width(150.dp)
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.width(35.dp))
                    Spacer(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(brush)

                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row() {
                    Spacer(
                        modifier = Modifier
                            .height(15.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .width(55.dp)
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Spacer(
                        modifier = Modifier
                            .height(15.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .width(55.dp)
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Spacer(
                        modifier = Modifier
                            .height(15.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .width(55.dp)
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Spacer(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(brush)

            )

        }



    }
}

@Composable
fun ShimmerGridImageItem(brush: Brush) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush)

        )


    }
}

@Composable
@Preview(showBackground = true)
fun ShimmerGridItemPreview() {
    ShimmerGridItem(
        brush = Brush.linearGradient(
            listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f),
            )
        )
    )
}

@Composable
@Preview(showBackground = true)
fun ShimmerGridItemImagePreview() {
    ShimmerGridImageItem(
        brush = Brush.linearGradient(
            listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f),
            )
        )
    )
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun ShimmerGridItemDarkPreview() {
    ShimmerGridItem(
        brush = Brush.linearGradient(
            listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f),
            )
        )
    )
}