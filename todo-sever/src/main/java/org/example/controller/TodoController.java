package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.model.TodoResponse;
import org.example.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {
    private final TodoService service;

    @PostMapping
    // 리스트에 투두아이템을 추가
    // requestBody 로 TodoRequest 를 받는다
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request){
        System.out.println("CREATE");
        // 타이틀이 없다면 정상적인 요청이 아니기에 없으면 잘못된 요청이라고 응답을 내린다
        if(ObjectUtils.isEmpty(request.getTitle()))
            return ResponseEntity.badRequest().build();
        // order와 completed 가 없다하여 잘못된 값이 아니기에 order가 없다면 기본 디폴트 '0L'
        if(ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);
        // completed 가 없다면 default false 로
        if(ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);
        // request 를 서비스에 add 하여 todoEntity를 result 값으로 받고
        TodoEntity result = this.service.add(request);
        // 받은 result 를 todoResponse에 한번 매핑하여 내려준다
        return ResponseEntity.ok(new TodoResponse(result));
    }


    @GetMapping("{id}")
    // 경로로 받은 id 값을 쓰기 위해 pathvariable 파라미터로 받고
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        System.out.println("READ ONE");
        // 서비스에서 find id
        TodoEntity result = this.service.searchById(id);
        // result 매핑
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping
    // 전체를 조회하는 api
    public ResponseEntity<List<TodoResponse>> readAll() {
        System.out.println("READ ALL");
        List<TodoEntity> list = this.service.searchAll();
        // todoResponse 로 매핑해야하기에 리스트로 받은 값을 리스트로 바꾼다
        List<TodoResponse> response = list.stream().map(TodoResponse::new)
                                                    .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
        System.out.println("UPDATE");
        // id 를 통해 request 한 값으로 수정한다
        TodoEntity result = this.service.updateById(id, request);
        // 매핑하여 내려준다
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        System.out.println("DELETE");
        this.service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<TodoEntity> deleteALL() {
        System.out.println("DELETE ALL");
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
