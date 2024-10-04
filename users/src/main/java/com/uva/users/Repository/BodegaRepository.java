package com.uva.users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uva.users.model.Bodega;

public interface BodegaRepository extends JpaRepository<Bodega, Integer> {
}