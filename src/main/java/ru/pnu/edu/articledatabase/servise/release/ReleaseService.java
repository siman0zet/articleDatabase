package ru.pnu.edu.articledatabase.servise.release;

import ru.pnu.edu.articledatabase.entity.Release;
import ru.pnu.edu.articledatabase.servise.release.argument.ReleaseCreateArgument;
import ru.pnu.edu.articledatabase.servise.release.argument.ReleaseUpdateArgument;

import java.util.List;

public interface ReleaseService {
    Release create(ReleaseCreateArgument argument);
    void delete(Integer id);
    Release update(Integer id, ReleaseUpdateArgument argument);
    List<Release> getAll();
}
