package com.lupus.contentcalendar.controller;


import com.lupus.contentcalendar.model.Content;
import com.lupus.contentcalendar.repository.ContentCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository repository;

    @Autowired
    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll(){
        return  repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content){
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id,@RequestBody Content content){
        Optional<Content> inDbContent = repository.findById(id);
        if(inDbContent.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found");
        }
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        Optional<Content> to_delete = repository.findById(id);
        if(to_delete.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found");
        }
        repository.delete(id);
    }


}


    //Create Read Update Delete CRUD
