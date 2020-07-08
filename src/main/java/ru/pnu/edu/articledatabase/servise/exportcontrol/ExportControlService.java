package ru.pnu.edu.articledatabase.servise.exportcontrol;

import ru.pnu.edu.articledatabase.entity.ExportControl;
import ru.pnu.edu.articledatabase.servise.exportcontrol.argument.ExportControlCreateArgument;
import ru.pnu.edu.articledatabase.servise.exportcontrol.argument.ExportControlUpdateArgument;

import java.util.List;

public interface ExportControlService {
    ExportControl create(ExportControlCreateArgument argument);
    void delete(Integer id);
    ExportControl update(Integer id, ExportControlUpdateArgument argument);
    List<ExportControl> getAll();
}
