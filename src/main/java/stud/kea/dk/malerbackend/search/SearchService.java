package stud.kea.dk.malerbackend.search;

import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.paint.model.Paint;
import stud.kea.dk.malerbackend.paint.repository.PaintRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final PaintRepository paintRepository;

    public SearchService(PaintRepository paintRepository) {
        this.paintRepository = paintRepository;
    }

    public List<SearchDto> searchByNameOrItemNo(String query) {
        List<Paint> paints = paintRepository.findByNameContainingIgnoreCaseOrPaintNo_ItemNoContainingIgnoreCase(query);

        return paints.stream().map(paint -> new SearchDto(
                paint.getName(),
                paint.getPaintNo().getItemNo(),
                paint.getPaintNo().getLiters(),
                paint.getCategory(),
                paint.getPrice(),
                paint.getShine()
        )).collect(Collectors.toList());
    }
}
