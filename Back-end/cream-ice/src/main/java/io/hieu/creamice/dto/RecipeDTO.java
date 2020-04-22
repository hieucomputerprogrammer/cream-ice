package io.hieu.creamice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.hieu.creamice.utils.OffsetDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDTO implements Serializable {
    private Long id;
    private Long iceCreamId;
    private String title;
    private String description;
    private Double price;
    private String status;
    private Integer viewCount;
    private String imageInBase64;
    private String details;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    private OffsetDateTime createdAt;
}