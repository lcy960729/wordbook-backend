package com.example.wordbook.global.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface IgnoreUnmappedMapperConfig {
}
