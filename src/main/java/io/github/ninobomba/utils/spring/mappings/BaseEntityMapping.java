package io.github.ninobomba.utils.spring.mappings;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "id", ignore = true)
@Mapping(target = "createdBy", ignore = true)
@Mapping(target = "createdDate", ignore = true)
@Mapping(target = "lastModifiedBy", ignore = true)
@Mapping(target = "lastModifiedDate", ignore = true)
@Mapping(target = "version", ignore = true)
@Mapping(target = "status", ignore = true)
public @interface BaseEntityMapping {
}
