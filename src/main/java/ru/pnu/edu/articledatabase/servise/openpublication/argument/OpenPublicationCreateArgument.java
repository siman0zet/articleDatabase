package ru.pnu.edu.articledatabase.servise.openpublication.argument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
@AllArgsConstructor
public class OpenPublicationCreateArgument {
    Integer number;
    Date date;
}
