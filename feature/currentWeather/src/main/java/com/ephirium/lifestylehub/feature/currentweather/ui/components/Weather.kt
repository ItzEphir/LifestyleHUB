package com.ephirium.lifestylehub.feature.currentweather.ui.components

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.IntrinsicSize.Max
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ephirium.lifestylehub.feature.currentweather.R
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiModel
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiModel.IconMode.DAY
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiModel.IconMode.NIGHT

@Composable
fun Weather(
    modifier: Modifier = Modifier,
    weatherUiModel: WeatherUiModel,
    onRefreshClick: () -> Unit,
) {
    val containerColor = when (weatherUiModel.iconMode) {
        DAY   -> MaterialTheme.colorScheme.secondaryContainer
        NIGHT -> MaterialTheme.colorScheme.secondary
    }
    Card(
        modifier = modifier, shape = RoundedCornerShape(24.dp), colors = cardColors(
            containerColor = containerColor,
            contentColor = contentColorFor(backgroundColor = containerColor)
        )
    ) {
        Column(modifier = modifier) {
            Box(modifier = modifier) {
                Text(
                    text = weatherUiModel.city,
                    style = MaterialTheme.typography.headlineLarge,
                    color = LocalContentColor.current,
                    modifier = Modifier.align(Alignment.Center)
                )
                
                IconButton(modifier = Modifier.align(Alignment.TopEnd), onClick = onRefreshClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.cached), contentDescription = null
                    )
                }
            }
            
            Row(
                modifier = modifier
                    .padding(horizontal = 24.dp)
                    .height(Max),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clip(CircleShape)
                            .background(
                                when (weatherUiModel.iconMode) {
                                    DAY   -> MaterialTheme.colorScheme.primaryContainer
                                    NIGHT -> MaterialTheme.colorScheme.onPrimaryContainer
                                }
                            )
                    ) {
                        Image(
                            modifier = Modifier
                                .width(128.dp)
                                .height(128.dp),
                            painter = painterResource(id = weatherUiModel.iconId),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Text(
                        text = weatherUiModel.status,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .align(Alignment.CenterHorizontally).widthIn(0.dp, 128.dp),
                        textAlign = TextAlign.Center,
                        maxLines = 4,
                    )
                }
                
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight(),
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${weatherUiModel.minTemperature} — ${weatherUiModel.maxTemperature} ",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = weatherUiModel.temperature,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    
                    Text(
                        text = "${stringResource(id = com.ephirium.lifestylehub.androidBase.R.string.feels_like)} ${weatherUiModel.feelsLike}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp)
                    )
                }
            }
        }
        Box(modifier = modifier.padding(horizontal = 24.dp)) {
            
            
        }
    }
}

@RequiresApi(VERSION_CODES.S)
@PreviewDynamicColors
@PreviewLightDark
@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    MaterialTheme(colorScheme = dynamicDarkColorScheme(LocalContext.current)) {
        Surface {
            Weather(
                modifier = Modifier.fillMaxWidth(),
                weatherUiModel = WeatherUiModel(
                    temperature = "40",
                    maxTemperature = "45",
                    minTemperature = "35",
                    feelsLike = "37",
                    status = "Clear sky",
                    iconId = R.drawable.d01,
                    iconMode = DAY,
                    city = "Moscow"
                ),
                onRefreshClick = {},
            )
        }
    }
    
    
}