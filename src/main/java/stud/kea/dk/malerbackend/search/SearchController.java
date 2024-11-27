package stud.kea.dk.malerbackend.search;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("/search")
    public ResponseEntity<List<SearchDto>> searchItems(@RequestParam String query) {
        List<SearchDto> results = searchService.searchByNameOrItemNo(query);
        return ResponseEntity.ok(results);
    }
}
