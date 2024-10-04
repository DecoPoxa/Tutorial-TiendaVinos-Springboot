package com.uva.users.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.uva.users.Repository.BodegaRepository;
import com.uva.users.Repository.VinoConRelacionRepository;
import com.uva.users.Repository.VinoRepository;
import com.uva.users.model.Bodega;
import com.uva.users.model.Vino;
import com.uva.users.model.VinoConRelacion;
import com.uva.users.Exception.BodegaException;
import com.uva.users.Exception.VinoException;

@RestController
@RequestMapping("/TiendaVinos")
@CrossOrigin(origins = "*")
class VinoController {

    private final VinoRepository repository;
    private final VinoConRelacionRepository repository2;
    private final BodegaRepository repository3;

    VinoController(VinoRepository repository, VinoConRelacionRepository repository2, BodegaRepository repository3) {
        this.repository = repository;
        this.repository2 = repository2;
        this.repository3 = repository3;
    }

    // Tutorial 1.1 - 1
    @GetMapping()
    public String getVinos() {
        return "Mensaje desde getVinos";
    }

    // Tutorial 1.1 - 1
    @PutMapping("/{id}")
    public String putVinos(@RequestBody String body, @PathVariable("id") Long identificador) {
        return "Realizada operación Put. Con Id: " + identificador +
                ".\nContenido del cuerpo de la petición: " + body;
    }

    // Tutorial 1.1 - 1
    @PostMapping("/post")
    public String postVino(@RequestBody String body) {
        return "Vino actualizado: " + body;
    }

    // Tutorial 1.1 - 1
    @DeleteMapping("/delete")
    public String deleteVino(@RequestBody String body) {
        return "Vino eliminado: " + body;
    }

    //Tutorial 1.1 - 2
    @GetMapping(value = "/getHTML", produces = MediaType.TEXT_HTML_VALUE)
    public String getHTML() {
        return "<p style=color:red> Hola mundo";
    }

    //Tutorial 1.1 - 3
    @GetMapping(value = "/getJSON", produces = "application/json")
    public String getJSON() {
        return "{\n\"vino\":\"duero\",\n\"tipo\":\"blanco\"\n}";
    }

    //Tutorial 1.2
    @GetMapping(value = { "/cartaVinos", "/cartaVinos/{nombre}" })
    public String getCarta(@PathVariable(required = false) String nombre) {
        if (nombre != null) {
            return "vino con nombre: " + nombre;
        } else {
            return "Toda la carta de vinos";
        }
    }

    //Tutorial 1.2
    @GetMapping("/cartaVinos2")
    public String getCartaConQuery(@RequestParam List<String> nombres) {
        return "Nombre del vino: " + nombres;
    }

    //Tutorial 1.2 - 1
    @GetMapping(value = { "/dosVariables/{v1}_{v2}" })
    public String getDosVariables(@PathVariable String v1, @PathVariable String v2) {
        return "variable 1: " + v1 + "<br>variable 2: " + v2;
    }

    //Tutorial 1.2 - 1
    @GetMapping(value = { "/informacion/{id}" })
    public String getInformacion(@PathVariable Long id, @RequestParam(defaultValue = "precio") List<String> campos) {
        return "Id del vino: " + id + "<br>Campos: " + campos;
    }

    @PostMapping
    public String newVino(@RequestBody Vino newVino) {
        try {
            repository.save(newVino);
            return "Nuevo registro creado";
        } catch (Exception e) {
            throw new VinoException("Error al crear el nuevo registro.");
        }
    }

    @GetMapping("/conseguirVinos")
    public List<Vino> conseguirVinos() {
        return repository.findAll();
    }

    // Tutorial 1.4 - Ejercicio 1
    @GetMapping("/VinoPorNombre/{nombre}")
    public Vino getVinoPorNombre_Comercial(@PathVariable String nombre) {
        Vino vino = repository.findByNombreComercial(nombre)
                .orElseThrow(() -> new VinoException("no se ha encontrado el vino de nombre " + nombre));
        return vino;
    }

    // Tutorial 1.4 - Ejercicio 1
    @GetMapping("/VinoPorPrecio")
    public List<Vino> getVinoPorPrecio(@RequestParam Float precio1, @RequestParam Float precio2) {
        List<Vino> vinos = repository.findByPrecioBetween(precio1, precio2);
        return vinos;
    }

    // Tutorial 1.4 - Ejercicio 1
    @DeleteMapping("/BorrarPorDenominacion_Categoria")
    public List<Vino> deletePorDenominacion_Categoria(@RequestBody String json) {
        try {
            JSONObject jsonObjeto = new JSONObject(json);
            String denominacion = jsonObjeto.getString("denominacion");
            String categoria = jsonObjeto.getString("categoria");
            boolean existe = repository.existsVinoByDenominacionAndCategoria(denominacion, categoria);
            if (existe) {
                List<Vino> borrados = repository.deleteByDenominacionAndCategoria(denominacion, categoria);
                return borrados;
            } else {
                System.out.println("No existen vinos de la categoría y denominación");
                return List.of();
            }
        } catch (Exception e) {
            System.out.println(e);
            return List.of();
        }
    }

    // Tutorial 1.4 - Ejercicio 4
    @GetMapping("/VinoPorDenominacionOrdenadoPorNombre/{denominacion}")
    public List<Vino> getVinoPorDenominacionOrdenado(@PathVariable String denominacion) {
        List<Vino> vinos = repository.findByDenominacionOrdenadoNombreDesc(denominacion);
        return vinos;
    }

    // Tutorial 1.4 - Ejercicio 4
    @GetMapping("/VinoPorDenominacionYCategoria")
    public List<Vino> getVinoPorDenominacionYCategoria(@RequestParam String denominacion,
            @RequestParam String categoria) {
        List<Vino> vinos = repository.findByDenominacionYCategoria(denominacion, categoria);
        return vinos;
    }

    // Tutorial 1.4 - Ejercicio 4
    @PostMapping("/newBodega")
    public String newBodega(@RequestBody Bodega newBodega) {
        try {
            repository3.save(newBodega);
            return "Nuevo registro creado";
        } catch (Exception e) {
            throw new BodegaException("Error al crear el nuevo registro.");
        }
    }

    // Tutorial 1.4 - Ejercicio 4
    @PostMapping("/newVinoConRelacion")
    public String newVinoConRelacion(@RequestBody VinoConRelacion newVinoConRelacion) {
        try {
            repository2.save(newVinoConRelacion);
            return "Nuevo registro creado";
        } catch (Exception e) {
            throw new VinoException("Error al crear el nuevo registro.");
        }
    }

    // Tutorial 1.4 - Ejercicio 4
    @GetMapping(value = "/getBodega/{id}", produces = "application/json")
    public Optional<Bodega> getBodega(@PathVariable Integer id) {
        return repository3.findById(id);
    }

    // Tutorial 1.4 - Ejercicio 4
    @GetMapping(value = "/getVinoConRelacion/{id}", produces = "application/json")
    public Optional<VinoConRelacion> getVinoConRelacion(@PathVariable Integer id) {
        return repository2.findById(id);
    }

}
