package kr.ac.jejunu.exchange.Controller;

import kr.ac.jejunu.exchange.Model.User;
import kr.ac.jejunu.exchange.Repository.UserRepository;
import kr.ac.jejunu.exchange.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}")
    public User get(@PathVariable Integer id){
        return userService.getUser(id);

    }

    @GetMapping(value ="/list")
    public List<User> list(){
        return userService.getUserList();
    }
    //Todo 등록 성공 실패 여부 리턴 필요 설계여부에 따라 리턴값 달라질 수 있음
    @PostMapping
    public User create(@RequestBody User user){
      return userService.createUser(user);
    }

    //Todo 업데이트 성공 실패 여부 리턴 필요
    @PutMapping
    public void update(@RequestBody User user){
        userService.updateUser(user);
    }
    //Todo delete 성공 실패 여부 리턴 필요
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
      userService.deleteUser(id);
    }


}
