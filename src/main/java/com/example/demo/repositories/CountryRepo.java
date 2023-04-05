package com.example.demo.repositories;
import com.example.demo.Beans.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Integer> {


}
