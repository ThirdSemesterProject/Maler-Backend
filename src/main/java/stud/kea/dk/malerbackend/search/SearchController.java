package stud.kea.dk.malerbackend.search;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<List<SearchDto>> searchItems(@RequestParam String query) {
        List<SearchDto> results = searchService.searchByNameOrCategory(query);
        return ResponseEntity.ok(results);
    }
}
