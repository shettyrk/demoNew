package com.example.demo.Controllers;

import com.example.demo.Beans.Country;
import com.example.demo.Services.CountryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryServices countryServices;

    @GetMapping("/getcountries")
    public List<Country> getCountries(){
        return countryServices.getAllCountry();
    }
    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable(value="id") int id){
        try {
            Country country= countryServices.getCountryByID(id);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam(value="name") String countryName){
        try {
            Country country= countryServices.getCountryByName(countryName);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addcountry")
    public Country addCountry(@RequestBody Country country){
        return countryServices.addCountry(country);
    }
    @PutMapping("/updatecountry")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id")int id, @RequestBody Country country){
        try {
            var existingCountry = countryServices.getCountryByID(id);
            existingCountry.setCountryName(country.getCountryName());
            existingCountry.setCountryCapital(country.getCountryCapital());
            var updated_country = countryServices.updateCountry(existingCountry);
            return new ResponseEntity<Country>(country,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }
    @DeleteMapping("/deletecountry/{id}")
    public AddResponce deleteCountry(@PathVariable(value ="id") int id){
        return countryServices.deleteCountry(id);
    }

}
