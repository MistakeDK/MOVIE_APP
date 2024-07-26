package com.example.datnguyen.movie.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EpisodeCreationRequest {
    @NonNull
    String name;
    @NonNull
    String linkEmbed;
}
