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
    if(data instanceof Integer) {
      return data.toString();
//      return String.valueOf(data);
//      return data+"";
//      return ((Integer) data).toString();
    }

      //입력된 값이 Boolean일 경우
      if(data instanceof Boolean){
        return data.toString();
//        return Boolean.valueOf(data.toString()) ? "true" : "false";

    }

    //howto 입력된 값이 Object[]일 경우
      if(data instanceof Object[]) {
        Object[] arr = (Object[]) data;



        //howto 순회하면서 배열을 생성할 수 있다.         [1,2,3]  ; [1,2,null,"코드스테이츠"];
//      방법 1

        /*for (int i = 0; i < arr.length; i++) {
          arr[i] = stringify(arr[i]);
        }
        return Arrays.toString(arr).replaceAll(" ","");
*/
        // todo 방법 2
        if(arr.length == 0) return"[]";

        String sol ="";

        for (int i = 0; i < arr.length; i++) {
          if(i == arr.length-1){
            sol =sol + stringify(arr[i]);
          }else sol += stringify(arr[i]) + ",";
        }
          return "[" + sol + "]";

        //todo 방법 3



      }





    //howto 입력된 값이 HashMap일 경우

    if (data instanceof HashMap) {


      HashMap<Object, Object> map = (HashMap<Object, Object>) data;   //note data가 오브젝트형이니까 Hashmap 형으로 명시적 형변환을 할 필요가 있다.
      HashMap<Object, Object> result = new LinkedHashMap<>();
      //note 공식 : 스트림 혹은 인포문 쓰는 것을 권장
      for(Map.Entry<Object,Object> entry : map.entrySet()){
        String key = stringify(entry.getKey());
        String value = stringify(entry.getValue());
        result.put(key,value);
      }
      return result.toString().replaceAll("=",":").replaceAll(" ","");    //note 순서를 보장하지 않는 map >>> 순서를 보장해주는 LinkedHashMap을 이용하자
    }



    //지정되지 않은 타입의 경우에는 "null"을 리턴합니다.
    return "null";
  }
}
