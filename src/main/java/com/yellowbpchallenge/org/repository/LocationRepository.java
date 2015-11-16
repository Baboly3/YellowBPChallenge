package com.yellowbpchallenge.org.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.yellowbpchallenge.org.entity.Location;


public interface LocationRepository extends JpaRepository<Location, Long> {
}
