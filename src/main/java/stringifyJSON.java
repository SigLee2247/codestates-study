import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

/**
 * note
 *  1. Browser에 존재하는 stringify 함수를 직접 구현해 봅니다.
 *   stringify 함수는 input 값을 JSON 형식으로 변환합니다.
 *note
 * 2. stringify는 아래와 같이 작동합니다.
 * - Boolean이 input으로 주어졌을 경우
 * stringify(true);                // "true"
 * - String이 input으로 주어졌을 경우
 * stringify("foo");               // "foo"
 howto - null이 주어졌을 경우
 howto    stringify(null)                 // "null"
 * todo "" 표시가 붙여진다.
 * note
 * - HashMap이 input으로 주어졌을 경우
 * HashMap<Object, Object> map = new HashMap<>();
 * map.put("null", 2);
 * map.put("true", "false");
 * stringify(map);                // "{"null":2,"true":"false"}"
 * Map 자료형의 Key는 항상 String으로 저장됩니다. null은 입력할 수 없습니다.
 note
 note  예시에 해당되지 않는 자료형의 경우 모두 null을 반환합니다.
 *
 note 3. test/java/stringifyJSON_test.java의 테스트에서 어떤 input 값들이 주어지고, 어떻게 stringify 주어야 할지 생각해 보세요.

 note 4. 입력받은 전달인자의 타입은 instanceof 메서드를 활용합니다.
 note 5. 그냥 테스트 통과를 하고 싶으시다면, 다음과 같이 구현하면 될 거예요.
 note note ObjectMapper mapper = new ObjectMapper();
 note return mapper.writeValueAsString(data);
 note 위의 코드는 ObjectMapper 메소드로 이미 구현되어 있습니다. main 클래스에서 직접 사용해보세요!
 note 하지만 이 과제의 목적은 재귀를 공부하는 것이니, 처음부터 구현해봐야겠지요?:
 */

public class stringifyJSON {

  public String ObjectMapper(Object data) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(data);
  }

  public String stringify(Object data) {

    //입력된 값이 문자열일 경우

    //입력된 값이 Integer일 경우 : Integer -> String
    if(data instanceof Integer){
      return data.toString();
    }

    //입력된 값이 Boolean일 경우
    if(data instanceof Boolean){
      return data.toString();
    }

    //howto 입력된 값이 Object[]일 경우
      if(data instanceof Object[]) {
          //todo 탈출 조건 먼저 정하자
          String total = "";
          if(((Object[]) data).length == 0) return "[]";
          if(((Object[]) data).length == 1) return stringify(((Object[])data)[0]);

        Object[] tail = Arrays.copyOfRange((Object[])data,1,((Object[]) data).length);
        total =stringify(((Object[])data)[0])+ "," + stringify(tail);
        if(tail.length == 1 )
            return stringify(tail);
        return"[" + total +"]";


      }                   //note 0번지 배열이 필요한데 배열이 아니라고 뜨는 것이 문제



    //howto 입력된 값이 HashMap일 경우

    if (data instanceof HashMap) {
      //howto 탈출 조건
      if(((HashMap<?, ?>) data).isEmpty()) return "";

      Set<Map.Entry<Object, Object>> entrySet = ((HashMap<Object,Object>)data).entrySet();

      Iterator<Map.Entry<Object, Object>> entryIterator = entrySet.iterator();
      String result = "";
      result = result + "\"" + stringify(entryIterator.next().getKey()) + "\"" +":" + stringify(entryIterator.next().getValue()) ;
      return null;
//      while(entryIterator.hasNext()) {
//        Map.Entry<Object, Object> entry = entryIterator.next();
//        Object key = entry.getKey();
//        Object value = entry.getValue();

//      }
    }



    //지정되지 않은 타입의 경우에는 "null"을 리턴합니다.
    return "null";
  }
}
