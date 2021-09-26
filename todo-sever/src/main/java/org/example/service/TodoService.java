package org.example.service;


import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
/*
    1. 리스트 목록에 아이템을 추가
    2. 리스트목록중 특정 아이템을 조회
    3. 리스트 전체 목록을 조회
    4. 리스트 목록 중 특정 아이템을 수정
    5. 리스트 목록 중 특정 아이템을 삭제
    6. 리스트 전체 목록을 삭제
*/
    // 아이템을 추가하는 add (entity 를 받아 추가한다)
    public TodoEntity add(TodoRequest request){
        // 엔티티 클래스에 값을 입력한다
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());
        // 저장소에 데이터베이스 값이 입력된다
        return this.todoRepository.save(todoEntity);
    }
    // 아이디 기준으로 파라미터를 아이디로 받아 서치한다
    public TodoEntity searchById(Long id) {
        return this.todoRepository.findById(id)
                // 찾고자 하는 아디값이 없을 경우 notfound exception을 날리도록 한다 404
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    // 전체니까 리스트로 반환
    public List<TodoEntity> searchAll(){
        return this.todoRepository.findAll();
    }
    // 수정할 아이디값을 파라미터로 받아 엔티티를 수정한다. TodoRequest 로 어떤 값으로 바꿀지 파라미터로 받는다
    public TodoEntity updateById(Long id, TodoRequest request){
        TodoEntity todoEntity = this.searchById(id);
        // 위에서 아이디가 없을 경우 notfound 가 뜰것이다 아니라면 아래 수행
        // null 이 아닐경우 request 받은 값으로 엔티티를 set해준다
        if (request.getTitle() != null){
            todoEntity.setTitle(request.getTitle());
        }
        if (request.getOrder() != null) {
            todoEntity.setOrder(request.getOrder());
        }
        if (request.getCompleted() != null){
            todoEntity.setCompleted(request.getCompleted());
        }
        // update 같은 경우 save 메소드로 엔티티
        return this.todoRepository.save(todoEntity);
    }
    // 삭제할 아이디값을 파라미터로 받아 삭제
    public void deleteById(Long id){
        this.todoRepository.deleteById(id);
    }

    public void deleteAll() {
        this.todoRepository.deleteAll();
    }
}
