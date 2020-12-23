package com.rmurugaian.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("InterfaceNeverImplemented")
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}