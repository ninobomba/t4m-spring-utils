package io.github.ninobomba.utils.spring.mappings;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class DateMapper {

    @Named("localDateToSqlDate")
    public Date localDateToSqlDate(LocalDate localDate) {
        return localDate != null ? Date.valueOf(localDate) : null;
    }

    @Named("sqlDateToLocalDate")
    public LocalDate sqlDateToLocalDate(Date sqlDate) {
        return sqlDate != null ? sqlDate.toLocalDate() : null;
    }
}
