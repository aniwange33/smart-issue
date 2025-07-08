package com.amos.silog.resolution.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Aggregates all converters for resolutions.
 */
@Component
public class ResolutionConverters {
    public final ResolutionRequestConverter request;
    public final ResolutionResponseConverter response;

    @Autowired
    public ResolutionConverters(ResolutionRequestConverter request,
                               ResolutionResponseConverter response) {
        this.request = request;
        this.response = response;
    }
}