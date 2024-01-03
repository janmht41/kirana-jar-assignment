package com.assignment.KiranaService.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    Error error;
}
