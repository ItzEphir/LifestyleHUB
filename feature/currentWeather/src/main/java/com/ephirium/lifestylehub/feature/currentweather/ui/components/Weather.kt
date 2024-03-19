package com.ephirium.lifestylehub.feature.currentweather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ephirium.lifestylehub.feature.currentweather.R
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiModel
import com.ephirium.lifestylehub.feature.currentweather.presentation.model.WeatherUiStatus.Clear

@Composable
fun Weather(
    modifier: Modifier = Modifier,
    weatherUiModel: WeatherUiModel,
    onRefreshClick: () -> Unit,
) {
    Card(modifier = modifier, shape = RoundedCornerShape(24.dp)) {
        Column(modifier = modifier) {
            Box(modifier = modifier) {
                Text(
                    text = weatherUiModel.city,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
                
                IconButton(modifier = Modifier.align(Alignment.TopEnd), onClick = onRefreshClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.cached), contentDescription = null
                    )
                }
            }
            
            Row(
                modifier = modifier.padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceContainer)
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
                }
                
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "${weatherUiModel.maxTemperature} — ${weatherUiModel.minTemperature}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    Text(
                        text = weatherUiModel.temperature,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    
                }
            }
        }
        Row(modifier = Modifier.padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically){
            Text(
                text = weatherUiModel.status.description,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 5.dp).weight(1f)
            )
            
            Text(
                text = "Feels like ${weatherUiModel.feelsLike}",
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    Surface {
        Weather(
            modifier = Modifier.fillMaxWidth(),
            weatherUiModel = WeatherUiModel(
                temperature = "40",
                maxTemperature = "45",
                minTemperature = "35",
                feelsLike = "37",
                status = Clear,
                iconId = R.drawable.d01,
                city = "Moscow"
            ),
            onRefreshClick = {},
        )
    }
    
}