package com.example.TrabajoGrapghQL.controllers;

import com.example.TrabajoGrapghQL.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LibroController {
    @Autowired
    private LibroRepository libroRepository;
}
