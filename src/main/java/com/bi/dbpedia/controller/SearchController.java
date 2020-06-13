package com.bi.dbpedia.controller;


import com.bi.dbpedia.dto.EntityParam;
import com.bi.dbpedia.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/entity/{page}/{size}")
    public void search(@RequestBody EntityParam entityParam, @PathVariable int page, @PathVariable int size) {
        searchService.searchEntity(entityParam, page, size);
    }
}
