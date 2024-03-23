package test.java.com.ephirium.lifestylehub.data

import com.ephirium.lifestylehub.api.places.dto.placeResponse.*
import com.ephirium.lifestylehub.api.places.service.PlaceService
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.Ok
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

object MockPlaceService : PlaceService {
    // Places for Moscow lat and lon
    override suspend fun getPlaces(
        lat: Float,
        lon: Float,
        limit: Int
    ): ResponseResult<PlacesResponse> {
        delay(2.seconds)
        return Ok(
            PlacesResponse(
                PlacesHeaderResponse("https://api.foursquare.com/v3/places/search?cursor=c3I6MTA&radius=20000&ll=55.75222%2C37.61556&fields=fsq_id%2Cname%2Clocation%2Ccategories%2Cdistance&limit=5"),
                PlacesBodyResponse(
                    listOf(
                        PlaceDto(
                            id = "4bf7fa734a67c928e27624cf",
                            location = Location(
                                country = "RU", address = null, addressExtended = null
                            ),
                            categories = listOf(
                                Category(
                                    name = "Парк", pluralName = "Парки"
                                )
                            ),
                            name = "Александровский сад",
                            cursor = null,
                        ), PlaceDto(
                            id = "4bb3345942959c74d79d212c",
                            location = Location(
                                country = "RU", address = null, addressExtended = null
                            ),
                            categories = listOf(
                                Category(
                                    name = "Площадь", pluralName = "Площади"
                                )
                            ),
                            name = "Красная площадь",
                            cursor = null,
                        ), PlaceDto(
                            id = "4d4069bec5eaa1cd8a6fa150",
                            categories = listOf(
                                Category(
                                    name = "Пешеходная зона", pluralName = "Пешеходные зоны"
                                )
                            ),
                            location = Location(
                                country = "RU", address = null, addressExtended = null
                            ),
                            name = "Никольская улица",
                            cursor = null,
                        ), PlaceDto(
                            id = "56673abb38fa23ef7c48d22b",
                            categories = listOf(
                                Category(
                                    name = "Бутик", pluralName = "Бутики"
                                )
                            ),
                            location = Location(
                                country = "RU",
                                address = "Третьяковский Пр., 2",
                                addressExtended = null
                            ),
                            name = "Dolce & Gabbana",
                            cursor = null,
                        ), PlaceDto(
                            id = "4c1448d9f1e0b7133dcc34bc",
                            categories = listOf(
                                Category(
                                    name = "Музыкальное заведение",
                                    pluralName = "Музыкальные заведения"
                                ), Category(
                                    name = "Театр", pluralName = "Театры"
                                )
                            ),
                            location = Location(
                                country = "RU",
                                address = "Камергерский пер., 3",
                                addressExtended = "оф. 38"
                            ),
                            name = "МХТ им. Чехова",
                            cursor = null,
                        )
                    )
                )
            )
        )
    }
    
    override suspend fun getPlacesPage(
        lat: Float,
        lon: Float,
        page: String?,
        perPage: Int,
    ): ResponseResult<PlacesResponse> {
        if (page == null) {
            return getPlaces(lat, lon)
        }
        return Ok(
            PlacesResponse(
                PlacesHeaderResponse(
                    "https://api.foursquare.com/v3/places/search?cursor=c3I6NQ&radius=20000&ll=55.75222,37.61556&fields=fsq_id,name,location,categories,distance&limit=5"
                ), PlacesBodyResponse(
                    listOf(
                        PlaceDto(
                            id = "582727350b057e6915a9c6c9",
                            name = "Бомонд Лаунж",
                            location = Location(
                                country = "RU",
                                address = "Никольская Ул., д. 25",
                                addressExtended = "эт. 5",
                            ),
                            categories = listOf(
                                Category(
                                    name = "Кальянная", pluralName = "Кальянные"
                                )
                            ),
                            cursor = page,
                        ), PlaceDto(
                            id = "4ff82722e4b0d48ad322b6fb",
                            name = "Галерея искусства стран Европы и Америки XIX—XX веков",
                            location = Location(
                                country = "RU",
                                address = "Волхонка Ул., д. 14",
                                addressExtended = "корп. 6",
                            ),
                            categories = listOf(
                                Category(
                                    name = "Музей", pluralName = "Музеи"
                                )
                            ),
                            cursor = page,
                        ), PlaceDto(
                            id = "59c2249aa6fe4d16705f2c94",
                            name = "Концертный зал «Зарядье»",
                            location = Location(
                                country = "RU", address = null, addressExtended = null
                            ),
                            categories = listOf(
                                Category(
                                    name = "Концертный зал", pluralName = "Концертные залы"
                                )
                            ),
                            cursor = page,
                        ), PlaceDto(
                            id = "4cc26bead43ba1435fdc58f8",
                            name = "АКАДЕМИЧЕСКИЙ МУЗЫКАЛЬНЫЙ КОЛЛЕДЖ при МГК им. П.И. ЧАЙКОВСКОГО",
                            location = Location(
                                country = "RU",
                                address = "Мерзляковский пер., 11",
                                addressExtended = null
                            ),
                            categories = listOf(
                                Category(
                                    name = "Музыкальная школа", pluralName = "Музыкальные школы"
                                )
                            ),
                            cursor = page,
                        ), PlaceDto(
                            id = "4bc090ca920eb7132e14192c",
                            name = "Парк искусств «Музеон»",
                            location = Location(
                                country = "RU", address = null, addressExtended = null
                            ),
                            categories = listOf(
                                Category(
                                    name = "Ресторан", pluralName = "Рестораны"
                                ), Category(
                                    name = "Парк", pluralName = "Парки"
                                ), Category(
                                    name = "Гостиница", pluralName = "Гостиницы"
                                )
                            ),
                            cursor = page,
                        )
                    )
                )
            )
        )
    }
    
}