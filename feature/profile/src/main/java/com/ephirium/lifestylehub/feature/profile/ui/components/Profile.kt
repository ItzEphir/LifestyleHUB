package com.ephirium.lifestylehub.feature.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ephirium.lifestylehub.feature.profile.presentation.model.UserUiModel

@Composable
fun Profile(userUiModel: UserUiModel, modifier: Modifier = Modifier) {
    println(userUiModel)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = userUiModel.avatar,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(192.dp).clip(RoundedCornerShape(32.dp)),
            contentScale = ContentScale.FillBounds
        )
        
        Text(
            text = userUiModel.login,
            style = MaterialTheme.typography.displaySmall,
        )
        
        Text(
            text = userUiModel.name,
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}