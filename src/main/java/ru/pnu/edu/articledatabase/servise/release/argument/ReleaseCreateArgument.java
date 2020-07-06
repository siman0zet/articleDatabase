package ru.pnu.edu.articledatabase.servise.release.argument;


import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class ReleaseCreateArgument {

    Integer year;
    Integer number;
}
