package test.java.com.ephirium.lifestylehub.api.places

import com.ephirium.lifestylehub.api.places.utils.mapResponseLink
import org.junit.Test

class ResponseLinkMapperTest {
    @Test
    fun nullResponseLinkTest() {
        val testData: String? = null
        val actual = testData.mapResponseLink()
        assert(actual == null)
    }
    
    @Test
    fun validResponseLinkTest() {
        val testData =
            "<https://api.foursquare.com/v3/places/search?cursor=c3I6MTA&radius=20000&ll=55.75222%2C37.61556&fields=fsq_id%2Cname%2Clocation%2Ccategories%2Cdistance&limit=5>; rel=\"next\""
        val actual = testData.mapResponseLink()
        val expected = "https://api.foursquare.com/v3/places/search?cursor=c3I6MTA&radius=20000&ll=55.75222%2C37.61556&fields=fsq_id%2Cname%2Clocation%2Ccategories%2Cdistance&limit=5"
        assert(actual == expected)
    }
}