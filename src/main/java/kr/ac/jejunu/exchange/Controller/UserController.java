package kr.ac.jejunu.exchange.Controller;

import kr.ac.jejunu.exchange.Repository.UserRepository;
import kr.ac.jejunu.exchange.Model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public User get(@PathVariable String id){
        return userRepository.findById(id).get();
    }

    @GetMapping(value ="/list")
    public List<User> list(){
        return userRepository.findAll();
    }
    //Todo 등록 성공 실패 여부 리턴 필요 설계여부에 따라 리턴값 달라질 수 있음
    @PostMapping
    public User create(@RequestBody User user){
        return userRepository.save(user);
    }

    //Todo 업데이트 성공 실패 여부 리턴 필요
    @PutMapping
    public void update(@RequestBody User user){
        userRepository.save(user);
    }
    //Todo delete 성공 실패 여부 리턴 필요
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        userRepository.delete(userRepository.findById(id).get());
    }
}
