import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.*;
class RestaurantServiceTest {
    RestaurantService service=new RestaurantService();
    Restaurant restaurant;
    static LocalTime openingTime;
    static LocalTime closingTime;
    @BeforeAll
    public static void timeSetup()
    {
        openingTime=LocalTime.parse("09:00:00");
        closingTime=LocalTime.parse("11:00:00");
    }
    @BeforeEach
    void setUp()
    {
        restaurant=service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException
    {
        restaurant=service.findRestaurantByName("Amelie's cafe");
        assertEquals(restaurant,service.findRestaurantByName("Amelie's cafe"));
        //WRITE UNIT TEST CASE HERE
    }
    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException
    {
        assertThrows(restaurantNotFoundException.class,()->(service).findRestaurantByName("AmeLie's cafe1"));
        //WRITE UNIT TEST CASE HERE
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException
    {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }
    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException
    {
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }
    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1()
    {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

}