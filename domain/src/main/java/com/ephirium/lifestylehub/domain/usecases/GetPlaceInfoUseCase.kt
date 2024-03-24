package com.ephirium.lifestylehub.domain.usecases

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.Ok
import com.ephirium.lifestylehub.common.onOk
import com.ephirium.lifestylehub.domain.model.placeInfo.PlaceInfo
import com.ephirium.lifestylehub.domain.repositories.local.PlaceInfoLocalRepository
import com.ephirium.lifestylehub.domain.repositories.remote.PlaceInfoRemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetPlaceInfoUseCase(
    private val placeInfoRemoteRepository: PlaceInfoRemoteRepository,
    private val placeInfoLocalRepository: PlaceInfoLocalRepository,
) {
    suspend operator fun invoke(id: String, languageCode: String): Flow<ResponseResult<PlaceInfo>> {
        val local = placeInfoLocalRepository.getPlaceInfo(id, languageCode)
        val last = local.last()
        return if (last != null) {
            local.map { placeInfo -> Ok(placeInfo ?: throw Exception()) }
                .catch { throwable -> throwable.printStackTrace() }
        } else {
            placeInfoRemoteRepository.getPlaceInfo(id, languageCode).map { responseResult ->
                responseResult.onOk { placeInfo ->
                    placeInfoLocalRepository.postPlaceInfo(placeInfo)
                        .launchIn(CoroutineScope(Dispatchers.IO))
                }
            }
        }
    }
}