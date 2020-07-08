package ru.pnu.edu.articledatabase.servise.openpublication;

import ru.pnu.edu.articledatabase.entity.OpenPublication;
import ru.pnu.edu.articledatabase.servise.openpublication.argument.OpenPublicationCreateArgument;
import ru.pnu.edu.articledatabase.servise.openpublication.argument.OpenPublicationUpdateArgument;

import java.util.List;

public interface OpenPublicationService {
    OpenPublication create(OpenPublicationCreateArgument argument);
    void delete(Integer id);
    OpenPublication update(Integer id, OpenPublicationUpdateArgument argument);
    List<OpenPublication> getAll();
}
