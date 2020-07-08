package ru.pnu.edu.articledatabase.servise.exportcontrol;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.pnu.edu.articledatabase.entity.ExportControl;
import ru.pnu.edu.articledatabase.entity.OpenPublication;
import ru.pnu.edu.articledatabase.repository.ExportControlRepository;
import ru.pnu.edu.articledatabase.servise.exportcontrol.argument.ExportControlCreateArgument;
import ru.pnu.edu.articledatabase.servise.exportcontrol.argument.ExportControlUpdateArgument;
import ru.pnu.edu.articledatabase.servise.openpublication.argument.OpenPublicationCreateArgument;
import ru.pnu.edu.articledatabase.servise.openpublication.argument.OpenPublicationUpdateArgument;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExportControlServiceImpl implements ExportControlService {
    private final ExportControlRepository repository;

    @Override
    @Transactional
    public ExportControl create(ExportControlCreateArgument argument) {
        return repository.save(ExportControl.builder()
                .date(argument.getDate())
                .number(argument.getNumber())
                .build());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Integer id) {
        repository.delete(repository.getOne(id));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ExportControl update(Integer id, ExportControlUpdateArgument argument) {
        ExportControl exportControl = repository.getOne(id);
        exportControl.setDate(argument.getDate());
        exportControl.setNumber(argument.getNumber());
        return repository.save(exportControl);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExportControl> getAll() {
        return repository.findAll();
    }
}
