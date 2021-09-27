package org.example.service;

import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // 목 객체를 사용하기 위해
class TodoServiceTest {

    // 직접적으로 목 객체를 사용
    @Mock
    private TodoRepository todoRepository;

    // 목을 주입받아서 사용
    @InjectMocks
    private TodoService todoService;

    /*
    * 목을 사용하는 이유
    * 1. 외부 시스템에 의존하지 않고 자체 테스트를 실행할 수 있어야 하기 때문에 사용
    *   유닛 테스트는 네트워크나 데이터베이스가 연결 안된다고 해서 유닛테스트도 함께 실행 불가능 하면 X
    * 2. 실제 데이터베이스 사용시 테스트 할때 마다 db에 값이 추가되고 수정하거나 삭제하는 일이 일어날 수 있는데
    *   db에는 민감한 정보가 포함되어 있는 경우가 많고 서비스에서 사용중인 데이터가 함부러 변경되는 큰일 나기
    *   때문에 테스트를 실행할때도 실제 db와 연결해서 테스트를 하지 않는다
    * */

    @Test
    void add() {
        // todoRepo 가 save메서드를 호출해서 todoEntity 값을 받으면
        when(this.todoRepository.save(any(TodoEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected= new TodoRequest();
        expected.setTitle("Test Title");

        TodoEntity actual = this.todoService.add(expected);
        assertEquals(expected.getTitle(),actual.getTitle());
    }

    @Test
    void searchById() {
        TodoEntity entity = new TodoEntity();
        entity.setId(123L);
        entity.setTitle("Title");
        entity.setOrder(0L);
        entity.setCompleted(false);
        Optional<TodoEntity> optional = Optional.of(entity);

        given(this.todoRepository.findById(anyLong()))
                .willReturn(optional);
        TodoEntity actual = this.todoService.searchById(123L);
        TodoEntity expected = optional.get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getOrder(), actual.getOrder());
        assertEquals(expected.getCompleted(), actual.getCompleted());
    }

    @Test
    public void searchByIdFailed() {
        given(this.todoRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, ()-> {
            this.todoService.searchById(123L);
        });
    }
}