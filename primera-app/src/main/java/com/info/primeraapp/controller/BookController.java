package com.info.primeraapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Anotacion a nivel de clase
public class BookController {
    //Get --> Obtener
    @GetMapping("/aplicacion/v1/despedida") //Anotacion a nivel de metodo
    public String goodByeWorld(){
        return "Adios mundo cruel";
    }

    @GetMapping("/aplicacion/v1/saludo")
    public String helloWorld(@RequestParam(required = true,name = "nombre") String nombre){
        //Anotacion a nivel de atributo
      return "Hello " + nombre;
    }

}
