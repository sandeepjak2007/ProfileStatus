package com.sandeep.profilestatus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MyImage(modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(R.drawable.round_person),
        contentDescription = "Round Person",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(120.dp)
            .aspectRatio(1f)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithCache {
                val path = Path()
                path.addOval(
                    Rect(
                        topLeft = Offset.Zero,
                        bottomRight = Offset(
                            size.width,
                            size.height
                        )
                    )
                )
                onDrawWithContent {
                    clipPath(path) {
                        this@onDrawWithContent.drawContent()
                    }
                    val dotSize = size.width / 8f
                    drawCircle(
                        Color.Black,
                        radius = dotSize,
                        center = Offset(
                            size.width - dotSize,
                            size.height - dotSize
                        ),
                        blendMode = BlendMode.Clear
                    )
                    drawCircle(
                        Color(0xFF008000),
                        radius = dotSize * 0.8f,
                        center = Offset(
                            size.width - dotSize,
                            size.height - dotSize
                        )
                    )
                }
            }
    )
}