package kr.ac.jejunu.exchange.Controller;

import kr.ac.jejunu.exchange.Model.User;
import kr.ac.jejunu.exchange.Repository.UserRepository;
import kr.ac.jejunu.exchange.Service.UserService;
import kr.ac.jejunu.exchange.Util.StateCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public Object join(@RequestBody User user){
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setType(1);
            userService.createUser(user);

        }catch (Exception e){
            //TODO 추후 아이디 중복 체크 기능 추가 (임시방편)
//            return  new StateCode("409", "이미 가입된 아이디가 존재합니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT ).body(null);
        }
        return new StateCode("200", "회원가입에 성공하였습니다.");
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
