package com.backend.goty.controllers;

import com.backend.goty.entities.Goty;
import com.backend.goty.services.GotyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@Slf4j
@RequestMapping("/api")
public class GotyController {


    @Autowired
    private GotyService gotyServiceObj;


    @GetMapping("/goty")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity getGotys(){
        List<Goty> listaGoty;
        Map<String, Object> response = new HashMap<>();

        try{
            listaGoty = gotyServiceObj.findAll();
            log.info(listaGoty.toString());
        } catch (DataAccessException de){
            response.put("mensaje", "Error al consultar la base de datos");
            response.put("error", de.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(listaGoty, HttpStatus.OK);
    }



    //Cuando se realiza un voto para un juego.
    @PostMapping("/goty/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity postGotys(@PathVariable("id") Long id){
        Map<String, Object> response = new HashMap<>();

        Goty goty;
        Goty gotyUpdated;

        //Se realiza la busqueda a traves del id
        try{
            goty = gotyServiceObj.findById(id);
        }catch (DataAccessException de){
            response.put("mensaje", "Error al consultar la base de datos");
            response.put("error", de.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //Si el juego no ha sido encontrado retorna respuesta negativa
        if (goty == null){
            response.put("mensaje", "El Juego con ID: " + id.toString() + " no existe en la base de datos");
            log.error("Se usa un id invalido");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }

        try{
            //Se aumenta en 1 la cantidad del voto
            goty.setVotos(goty.getVotos() + 1);
            gotyUpdated = gotyServiceObj.save(goty);
        } catch (DataAccessException de){
            response.put("mensaje", "Error al consultar la base de datos");
            response.put("error", de.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        response.put("mensaje", "El voto se ha realizado con exito");
        response.put("goty", gotyUpdated);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
