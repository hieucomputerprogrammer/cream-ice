package io.hieu.creamice.controllers;

import io.hieu.creamice.dto.IceCreamDTO;
import io.hieu.creamice.services.IIceCreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:4200")
@RestController
public class IceCreamController {
    @Autowired
    private IIceCreamService IIceCreamService;

//    @RequestMapping(value = "/iceCreams", method = RequestMethod.GET)
//    public ResponseEntity<List<IceCreamDTO>> findAllIceCreams() {
//        List<IceCreamDTO> iceCreams = IIceCreamService.findAll();
//        if (iceCreams.isEmpty()) {
//            return new ResponseEntity<>(iceCreams, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(iceCreams, HttpStatus.OK);
//    }

    @GetMapping(value = "/ice-creams")
    public Page<IceCreamDTO> findAllIceCreams(Pageable pageable) {
        return IIceCreamService.findAll(pageable);
    }

    @GetMapping(value = "/ice-creams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IceCreamDTO> findIceCreamById(@PathVariable("id") Long id) {
        Optional<IceCreamDTO> iceCream = IIceCreamService.findById(id);
        if (iceCream.isPresent()) {
            return new ResponseEntity<>(iceCream.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/ice-creams")
    public ResponseEntity<IceCreamDTO> createIceCream(@RequestBody IceCreamDTO iceCreamDTO) {
        iceCreamDTO = IIceCreamService.create(iceCreamDTO);
        return new ResponseEntity<>(iceCreamDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/ice-creams/{id}")
    public ResponseEntity<IceCreamDTO> updateIceCream(@PathVariable("id") Long id, @RequestBody IceCreamDTO iceCreamDTO) {
        iceCreamDTO = IIceCreamService.update(iceCreamDTO, id);
        return new ResponseEntity<>(iceCreamDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/ice-creams/{id}")
    public ResponseEntity<Void> deleteIceCream(@PathVariable("id") Long id) {
        IIceCreamService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}