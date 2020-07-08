package ru.pnu.edu.articledatabase.servise.openpublication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.pnu.edu.articledatabase.entity.OpenPublication;
import ru.pnu.edu.articledatabase.repository.OpenPublicationRepository;
import ru.pnu.edu.articledatabase.servise.openpublication.argument.OpenPublicationCreateArgument;
import ru.pnu.edu.articledatabase.servise.openpublication.argument.OpenPublicationUpdateArgument;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenPublicationServiceImpl implements OpenPublicationService {

    private final OpenPublicationRepository repository;

    @Override
    @Transactional
    public OpenPublication create(OpenPublicationCreateArgument argument) {
        return repository.save(OpenPublication.builder()
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
    public OpenPublication update(Integer id, OpenPublicationUpdateArgument argument) {
        OpenPublication openPublication = repository.getOne(id);
        openPublication.setDate(argument.getDate());
        openPublication.setNumber(argument.getNumber());
        return repository.save(openPublication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpenPublication> getAll() {
        return repository.findAll();
    }
}
