package kr.ac.jejunu.exchange.Util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class StateCode {
    String stateCode;
    String messege;

    StateCode (){
    }

}
