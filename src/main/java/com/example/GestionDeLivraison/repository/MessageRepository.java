package com.example.GestionDeLivraison.repository;

import com.example.GestionDeLivraison.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}