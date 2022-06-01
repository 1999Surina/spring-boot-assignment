package com.zemoso.springbootassignment;

import com.zemoso.springbootassignment.dao.HotelsRepository;
import com.zemoso.springbootassignment.entity.Hotels;
import com.zemoso.springbootassignment.entity.Review;
import com.zemoso.springbootassignment.service.HotelsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HotelsApplicationTests {

	@Autowired
	private HotelsService hotelsService;

	@MockBean
    private HotelsRepository hotelsRepository;

	private List<Hotels> getSampleHotels(){
        List<Hotels> hotels = new ArrayList<>();
        Hotels hotels1 = new Hotels
                (1,"Sai Ram","Kakinada",
                        "","best for tiffins",5.5);
        Hotels hotels2 = new Hotels
                (2,"Ganesh","Rajmundry",
                        "www.ganesh.com","well known for meals",7.2);
        Hotels hotels3 = new Hotels
                (3,"Parlour","Rajmundry",
                        "www.parlour.com","well known for meals",6.2);
        hotels.add(hotels1);
        hotels.add(hotels2);
        hotels.add(hotels3);
        return hotels;
    }

	@Test
	void testFindAll() {
        List<Hotels> hotels = getSampleHotels();
        Mockito.when(hotelsRepository.findAll()).thenReturn(hotels);
        List<Hotels> listOfHotels = hotelsService.findAll();

        assertEquals(hotels.size(),listOfHotels.size());
	}

	@Test()
    void testFindById(){
	    int theId = 2;
	    List<Hotels> hotels = getSampleHotels();
	    Mockito.when(hotelsRepository.findById(theId)).thenReturn(Optional.of(hotels.get(theId)));

	    assertEquals(hotels.get(theId),hotelsService.findById(theId));

        theId = 1;
        Mockito.when(hotelsRepository.findById(theId)).thenReturn(Optional.of(hotels.get(theId)));

        assertEquals(hotels.get(theId),hotelsService.findById(theId));

        theId=4;
        Mockito.when(hotelsRepository.findById(theId)).thenReturn(Optional.empty());
        Hotels hotel = hotelsService.findById(theId);

        assertEquals(null,hotel);
    }

    @Test
    void testSave(){
        Hotels hotels4 = new Hotels
                (4,"Park","Rajmundry",
                        "www.park.com","well known for tiffins",6.7);
        hotelsService.save(hotels4);

        Mockito.verify(hotelsRepository,Mockito.times(1)).
                save(hotels4);
    }

    @Test
    void testDeleteById(){
	    int theId=3;
        hotelsService.deleteById(theId);

        Mockito.verify(hotelsRepository,Mockito.times(1)).
                deleteById(theId);
    }

    @Test
    void testAddReview(){
        int theId=1;
        Review review = new Review(1,theId,"sam","excellent");
        Hotels hotels = new Hotels
                (theId,"Sai Ram","Kakinada",
                        "","best for tiffins",5.5);
        Mockito.when(hotelsRepository.findById(theId)).
                thenReturn(Optional.of(hotels));
        hotelsService.addReview(review);


        Mockito.verify(hotelsRepository,Mockito.times(1)).
                save(hotels);
    }

}
