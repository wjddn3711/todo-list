package org.example.repository;

import org.example.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// jpa저장소를 상속받는다. jpa저장소의 제네릭으로 앞에는 데이터베이스 테이블과 연결될 객체인 entity 그리고 뒤에는 해당 객체의 id 에 해당하는 객체를 넣어준다
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}
