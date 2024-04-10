package com.codestates.coffee;

import com.codestates.validator.NotSpace;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import java.util.Optional;

/**
 * <h3>DTO 실습 과제용 Solution 코드 포함</h3>
 *
 * CoffeePatchDto는 실습 과제의 요구 사항에 대한 Solution 코드(유효성 검증 애너테이션)들이 필드에 포함이 되어 있습니다.
 *
 * <ul>
 *     <li>
 *      {@literal @}NotSpace: null 또는 공백이 아닐 경우에만 유효성 검증에 성공합니다.
 *     </li>
 *     <li>
 *      {@literal @}Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$"):
 *          <ul>
 *              <li>
 *                  한 단어이거나 단어와 단어 사이에 공백이 하나만 존재하는 경우에만 유효성 검증에 성공한다.
 *              </li>
 *              <li>
 *                  유효성 검증 성공 예) “Cafe Latte” (ㅇ), “Ca fe Latte” (ㅇ)
 *              </li>
 *              <li>
 *                  유효성 검증 실패 예) “Cafe       Latte” (X), “         Cafe Latte” (X), “ Cafe Latte       ” (X), “ Cafe Latte ” (X)
 *              </li>
 *          </ul>
 *     </li>
 *     <li>
 *      Optional&lt;{@literal @}Range(min= 100, max= 50000) Integer&gt;: null 또는 100에서 50000까지의 숫자만 유효성 검증에 성공한다.
 *     </li>
 * </ul>
 * <p>&nbsp;</p>
 * <h4>getter가 필요한 이유</h4>
 * 현재 버전은 CoffeeController에서 CoffeePostDto를 response body로 전달 하므로,
 * MappingJackson2HttpMessageConverter가 CoffeePostDto를 JSON 문자열로 변환할 때 getter를 사용합니다.
 *
 * <p>&nbsp;</p>
 * <h4>setCoffeeId()가 필요한 이유</h4>
 * 핸들러 메서드에서 path variable로 전달 받은 coffeeId를 setCoffeeId()를 통해 필드를 채움으로써 response body에 포함시킬 수 있습니다.
 *
 * @author 황정식
 * @version 1.0.0
 */
public class CoffeePatchDto {
    private long coffeeId;

    @NotSpace(message = "커피명(한글)은 공백이 아니어야 합니다.")
    private String korName;

    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$", message = "커피명(영문)은 영문이어야 합니다. 예) Cafe Latte")
    private String engName;

    private Optional<@Range(min= 100, max= 50000) Integer> price = Optional.empty();

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }

    public Optional<Integer> getPrice() {
        return price;
    }
}
