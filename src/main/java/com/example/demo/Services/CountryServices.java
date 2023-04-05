package com.example.demo.Services;

import com.example.demo.Beans.Country;
import com.example.demo.Controllers.AddResponce;
import com.example.demo.repositories.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service
public class CountryServices {
    @Autowired
    CountryRepo countryRepo;

    public List<Country> getAllCountry(){
        return countryRepo.findAll();
    }

    public Country getCountryByID(int id){
        return countryRepo.findById(id).get();
    }
    public Country getCountryByName(String name){
        List<Country> countries =countryRepo.findAll();
       Country country = null;

       for(Country con: countries){
           if (con.getCountryName().equalsIgnoreCase(name))
               country=con;
       }
       return country;
    }
    // utility method

    public int getMaxId(){
        return countryRepo.findAll().size()+1;
    }
    public Country addCountry(Country country){
        country.setId(getMaxId());
        countryRepo.save(country);
        return country;
    }


    public Country updateCountry(Country country){
        countryRepo.save(country);
        return country;
    }
    public AddResponce deleteCountry(int id){
        countryRepo.deleteById(id);

        var res = new AddResponce();
        res.setMsg("Country Deleted!");
        res.setId(id);
        return res;
    }

}
 