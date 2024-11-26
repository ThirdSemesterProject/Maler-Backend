package stud.kea.dk.malerbackend.paint.initdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.paint.model.Paint;
import stud.kea.dk.malerbackend.paint.repository.PaintRepository;
import stud.kea.dk.malerbackend.paintNo.repository.PaintNoRepository;


@Component
public class PaintInitdata implements CommandLineRunner {

    private final PaintRepository paintRepository;
    private final  PaintNoRepository paintNoRepository;

    public PaintInitdata(PaintRepository paintRepository, PaintNoRepository paintNoRepository) {
        this.paintRepository = paintRepository;
        this.paintNoRepository = paintNoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
