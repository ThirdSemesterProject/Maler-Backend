package stud.kea.dk.malerbackend.paint.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.paint.model.Paint;
import stud.kea.dk.malerbackend.paint.service.PaintService;

import java.util.List;


@RestController
@RequestMapping("api/paint")
@CrossOrigin(origins = "http://localhost:8080")
public class PaintController {

    private final PaintService paintService;

    public PaintController(PaintService paintService) {
        this.paintService = paintService;
    }

    @GetMapping("/getAllPaints")
    public List<Paint> getAllPaints() {
        return paintService.getAllPaints();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paint> getPaint(@PathVariable Long id) {
        Paint paint = paintService.getPaintById(id);
        if (paint != null) {
            return ResponseEntity.ok(paint);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/makePaint")
    public Paint makePaint(@RequestBody Paint paint) {
        return paintService.createPaint(paint);
    }
}
