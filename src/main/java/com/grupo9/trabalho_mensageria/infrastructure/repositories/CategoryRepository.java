package com.grupo9.trabalho_mensageria.infrastructure.repositories;

import com.grupo9.trabalho_mensageria.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
