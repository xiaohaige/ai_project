package com.empedocles.travelapp.data.mock

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.model.Image

object MockData {
    val mockTravelList = listOf(
        TravelModel(
            id = "1",
            title = "Paris",
            description = "The city of romance, perfect blend of art and fashion",
            city = "Paris",
            country = "France",
            category = "Europe",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/paris.jpg",
                    altText = "Eiffel Tower",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/paris2.jpg",
                    altText = "Louvre Museum",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "2",
            title = "Tokyo",
            description = "A metropolis where modernity meets tradition",
            city = "Tokyo",
            country = "Japan",
            category = "Asia",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/tokyo.jpg",
                    altText = "Tokyo Tower",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/tokyo2.jpg",
                    altText = "Senso-ji Temple",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "3",
            title = "New York",
            description = "The city that never sleeps, world's financial center",
            city = "New York",
            country = "United States",
            category = "North America",
            isBookmark = true,
            images = listOf(
                Image(
                    url = "https://example.com/newyork.jpg",
                    altText = "Statue of Liberty",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/newyork2.jpg",
                    altText = "Times Square",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "4",
            title = "Sydney",
            description = "The pearl of the Southern Hemisphere, perfect fusion of nature and urban life",
            city = "Sydney",
            country = "Australia",
            category = "Oceania",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/sydney.jpg",
                    altText = "Sydney Opera House",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/sydney2.jpg",
                    altText = "Bondi Beach",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "5",
            title = "London",
            description = "Historic capital with rich cultural heritage and modern charm",
            city = "London",
            country = "United Kingdom",
            category = "Europe",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/london.jpg",
                    altText = "Big Ben",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/london2.jpg",
                    altText = "Tower Bridge",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "6",
            title = "Dubai",
            description = "Ultra-modern desert metropolis with architectural wonders",
            city = "Dubai",
            country = "UAE",
            category = "Middle East",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/dubai.jpg",
                    altText = "Burj Khalifa",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/dubai2.jpg",
                    altText = "Palm Jumeirah",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "7",
            title = "Singapore",
            description = "Garden city with perfect blend of nature and technology",
            city = "Singapore",
            country = "Singapore",
            category = "Asia",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/singapore.jpg",
                    altText = "Marina Bay Sands",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/singapore2.jpg",
                    altText = "Gardens by the Bay",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "8",
            title = "Rio de Janeiro",
            description = "Vibrant city with stunning beaches and carnival spirit",
            city = "Rio de Janeiro",
            country = "Brazil",
            category = "South America",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/rio.jpg",
                    altText = "Christ the Redeemer",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/rio2.jpg",
                    altText = "Copacabana Beach",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "9",
            title = "Cape Town",
            description = "Natural beauty meets urban sophistication in South Africa",
            city = "Cape Town",
            country = "South Africa",
            category = "Africa",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/capetown.jpg",
                    altText = "Table Mountain",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/capetown2.jpg",
                    altText = "Robben Island",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "10",
            title = "Vancouver",
            description = "Coastal city surrounded by mountains and natural beauty",
            city = "Vancouver",
            country = "Canada",
            category = "North America",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/vancouver.jpg",
                    altText = "Stanley Park",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/vancouver2.jpg",
                    altText = "Capilano Suspension Bridge",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "11",
            title = "Bangkok",
            description = "City of temples, street food, and vibrant culture",
            city = "Bangkok",
            country = "Thailand",
            category = "Asia",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/bangkok.jpg",
                    altText = "Grand Palace",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/bangkok2.jpg",
                    altText = "Wat Arun",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "12",
            title = "Barcelona",
            description = "Artistic city with unique architecture and Mediterranean charm",
            city = "Barcelona",
            country = "Spain",
            category = "Europe",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/barcelona.jpg",
                    altText = "Sagrada Familia",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/barcelona2.jpg",
                    altText = "Park Güell",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "13",
            title = "Mumbai",
            description = "Bustling metropolis with rich cultural heritage",
            city = "Mumbai",
            country = "India",
            category = "Asia",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/mumbai.jpg",
                    altText = "Gateway of India",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/mumbai2.jpg",
                    altText = "Taj Mahal Palace",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "14",
            title = "Reykjavik",
            description = "Northern capital with geothermal wonders and northern lights",
            city = "Reykjavik",
            country = "Iceland",
            category = "Europe",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/reykjavik.jpg",
                    altText = "Hallgrímskirkja",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/reykjavik2.jpg",
                    altText = "Blue Lagoon",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "15",
            title = "Seoul",
            description = "Dynamic city where tradition meets cutting-edge technology",
            city = "Seoul",
            country = "South Korea",
            category = "Asia",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/seoul.jpg",
                    altText = "Gangnam District",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/seoul2.jpg",
                    altText = "Gyeongbokgung Palace",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "16",
            title = "Amsterdam",
            description = "Historic city of canals, art, and cycling culture",
            city = "Amsterdam",
            country = "Netherlands",
            category = "Europe",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/amsterdam.jpg",
                    altText = "Anne Frank House",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/amsterdam2.jpg",
                    altText = "Van Gogh Museum",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "17",
            title = "Buenos Aires",
            description = "City of tango, European architecture, and passionate culture",
            city = "Buenos Aires",
            country = "Argentina",
            category = "South America",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/buenosaires.jpg",
                    altText = "La Boca",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/buenosaires2.jpg",
                    altText = "Recoleta Cemetery",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "18",
            title = "Cairo",
            description = "Ancient city with pyramids and rich Egyptian history",
            city = "Cairo",
            country = "Egypt",
            category = "Africa",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/cairo.jpg",
                    altText = "Pyramids of Giza",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/cairo2.jpg",
                    altText = "Egyptian Museum",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "19",
            title = "Auckland",
            description = "City of sails with stunning harbors and volcanic landscapes",
            city = "Auckland",
            country = "New Zealand",
            category = "Oceania",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/auckland.jpg",
                    altText = "Sky Tower",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/auckland2.jpg",
                    altText = "Waiheke Island",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        ),
        TravelModel(
            id = "20",
            title = "Dubrovnik",
            description = "Medieval walled city with stunning Adriatic views",
            city = "Dubrovnik",
            country = "Croatia",
            category = "Europe",
            isBookmark = false,
            images = listOf(
                Image(
                    url = "https://example.com/dubrovnik.jpg",
                    altText = "Old Town Walls",
                    height = 800,
                    width = 1200,
                    isHeroImage = true
                ),
                Image(
                    url = "https://example.com/dubrovnik2.jpg",
                    altText = "Stradun",
                    height = 800,
                    width = 1200,
                    isHeroImage = false
                )
            )
        )
    )
} 