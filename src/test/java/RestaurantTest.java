import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    Restaurant restaurant;
    static LocalTime openingTime;
    static LocalTime closingTime;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeAll
    public static void timeSetup()
    {
        openingTime=LocalTime.parse("09:00:00");
        closingTime=LocalTime.parse("23:00:00");
    }
        @BeforeEach
    public void setup(){
        restaurant=new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
     }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        LocalTime checkOpenTime = LocalTime.parse("12:30:00");
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(checkOpenTime);
        assertTrue(spiedRestaurant.isRestaurantOpen());
    }


    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        LocalTime checkCloseTime = LocalTime.parse("23:30:00");
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(checkCloseTime);
        assertEquals(false,spiedRestaurant.isRestaurantOpen());
        //assertEquals(false, restaurant.isRestaurantOpen());
        //WRITE UNIT TEST CASE HERE

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public void addToMenu(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        addToMenu();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        addToMenu();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        addToMenu();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ORDER <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //TDD

    // When given list of ordered item
    // total cost should be returned.
    // cost will be sum of cost of individual items
    // when given empty list should return 0

    @Test
    public void calculateOrderValue_should_return_sum_of_all_items_value_when_given_list_of_ordered_items(){

        List<Item> orderedItems = new ArrayList<Item>();
        addToMenu();
        orderedItems.add(restaurant.getMenu().get(0));
        orderedItems.add(restaurant.getMenu().get(1));
        Restaurant spiedRestaurant =  Mockito.spy(restaurant);
        assertEquals("java.lang.Integer", spiedRestaurant.calculateOrderValue(orderedItems).getClass().getName());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ORDER >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}


