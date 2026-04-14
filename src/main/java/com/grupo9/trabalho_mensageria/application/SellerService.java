package com.grupo9.trabalho_mensageria.application;

import com.grupo9.trabalho_mensageria.domain.entities.Seller;
import com.grupo9.trabalho_mensageria.infrastructure.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public Seller save (Seller seller) {
        return sellerRepository.save(seller);
    }
}
