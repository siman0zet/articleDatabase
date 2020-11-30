package ru.pnu.edu.articledatabase.servise.arcticle.argument;

import javafx.scene.control.DatePicker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.hibernate.type.BlobType;
import ru.pnu.edu.articledatabase.entity.ExportControl;
import ru.pnu.edu.articledatabase.entity.Magazine;
import ru.pnu.edu.articledatabase.entity.OpenPublication;

import java.util.Date;

@Value
@Builder
@AllArgsConstructor
public class ArticleCreateArgument {
    String name;
    Integer study_year;
    String authors;
    String article_level;
    String stage;
    Magazine magazine_id;
    String page_range;
    Double price;
    String publication_type;
    Integer volume;
    Integer release;
    Date departure_date;
    String science_area;
    OpenPublication open_publication_id;
    ExportControl export_control_id;
}
