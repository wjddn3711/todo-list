package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private String url; // api 스펙을 정리할 떄 봤던 url 추가


    // 필드추가후 todoEntity 를 파라미터로 받는 생성자를 추가한다
    public TodoResponse(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.order = todoEntity.getOrder();
        this.completed = todoEntity.getCompleted();

        // url 은 엔티티에 없는 정보이기때문에 별도로 임의코드로 작성해준다
        this.url = "http://localhost:8080/" + this.id; // 해당 아이템의 값을 조회하는 api를 만들어 줄 것이기 때문
        // 하드코딩된 문자열이 코드내부에 들어와있는 것은 좋은 코드패턴은 아니다
        // base url 이나 port 가 변경될 때 마다 코드를 다시 재수성 배포해야하기 때문에 보통은 config 나 property로 관리해준다

    }

}
