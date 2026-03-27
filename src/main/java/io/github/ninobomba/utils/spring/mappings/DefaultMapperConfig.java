package io.github.ninobomba.utils.spring.mappings;

import org.mapstruct.Builder;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, builder = @Builder(disableBuilder = true))
public interface DefaultMapperConfig {
}
