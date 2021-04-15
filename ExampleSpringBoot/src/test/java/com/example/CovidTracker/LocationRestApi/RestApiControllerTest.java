package com.example.CovidTracker.LocationRestApi;

import com.example.CovidTracker.LocationModel.Location;
import com.example.CovidTracker.LocationService.virusRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestApiController.class)
public class RestApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private virusRepository repository;

    @Test
   public void TestGetAllLocations() throws Exception {
        List<Location>locationList = new ArrayList<>();
        locationList.add(new Location("macomb","michigan",25000,"250"));
        locationList.add(new Location("oakland","michigan",18000,"150"));
        locationList.add(new Location("washtenaw","michigan",6000,"80"));


        Mockito.when(repository.findAll()).thenReturn(locationList);
        String URL ="/api/location";
        MvcResult result =  mockMvc.perform(get(URL)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = result.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(locationList);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);


    }

    @Test
    void TestGetSpecificLocation() throws Exception {
        List<Location>locationList = new ArrayList<>();
        locationList.add(new Location("macomb","michigan",25000,"250"));
        locationList.add(new Location("oakland","michigan",18000,"150"));
        locationList.add(new Location("washtenaw","michigan",6000,"80"));
        locationList.add(new Location("alcona","michigan",900,"20"));
        locationList.add(new Location("allega","michigan",100,"8"));
        locationList.add(new Location("fresno","california",600,"90"));

        String county = "macomb";
        Mockito.when(repository.findById(county)).thenReturn(locationList.stream().findAny());

        String URL ="/api/location/state/county/{county}";
        MvcResult result =  mockMvc.perform(get(URL)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = result.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(locationList);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

    }





}