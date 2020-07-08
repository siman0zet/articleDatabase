package ru.pnu.edu.articledatabase.servise.release;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.pnu.edu.articledatabase.entity.Release;
import ru.pnu.edu.articledatabase.repository.ReleaseRepository;
import ru.pnu.edu.articledatabase.servise.release.argument.ReleaseCreateArgument;
import ru.pnu.edu.articledatabase.servise.release.argument.ReleaseUpdateArgument;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReleaseServiceImpl implements ReleaseService {
    private final ReleaseRepository repository;

    @Override
    @Transactional
    public Release create(ReleaseCreateArgument argument) {
        return repository.save(Release.builder()
                .number(argument.getNumber())
                .year(argument.getYear())
                .build());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Integer id) {
        repository.delete(repository.getOne(id));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Release update(Integer id, ReleaseUpdateArgument argument) {
        Release release = repository.getOne(id);
        release.setYear(argument.getYear());
        release.setNumber(argument.getNumber());
        return repository.save(release);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Release> getAll() {
        return repository.findAll();
    }




}
