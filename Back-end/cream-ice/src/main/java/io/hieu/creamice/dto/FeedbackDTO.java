package io.hieu.creamice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackDTO implements Serializable {
    private Long id;
    private Long customerID;
    private Long orderId;
    private String details;
    private Date createDate;
}