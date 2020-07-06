package ru.pnu.edu.articledatabase.servise.release;

import ru.pnu.edu.articledatabase.entity.Release;
import ru.pnu.edu.articledatabase.servise.release.argument.ReleaseCreateArgument;

import java.util.List;

public interface ReleaseService {
    Release create(ReleaseCreateArgument argument);
    List<Release> getAll();
}
