package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class TodoEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    // 타이틀이 없으면 db에 넣는 의미가 없기에 null 거짓으로
    private String title;

    @Column(name = "todoOrder", nullable = false)
    // order 키워드가 h2 데이터의 예약어이기 때문에 칼럼명을 별도로 지정해준다
    private Long order;

    @Column(nullable = false)
    private Boolean completed;
}
