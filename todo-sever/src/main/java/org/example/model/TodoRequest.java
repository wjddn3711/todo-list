package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TodoRequest {
    private String title;
    private Long order;
    private Boolean completed; // update 할때 completed 완료 여부도 값을 변경할 수 있기 때문에
}
