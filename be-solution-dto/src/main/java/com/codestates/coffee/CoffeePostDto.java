package com.codestates.coffee;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <h3>DTO 실습 과제용 Solution 코드 포함</h3>
 *
 * CoffeePostDto는 실습 과제의 요구 사항에 대한 Solution 코드(유효성 검증 애너테이션)들이 필드에 포함이 되어 있습니다.
 *
 * <h4>유효성 검증에 사용된 애너테이션</h4>
 * <ul>
 *     <li>
 *      {@literal @}NotBlank: null, 공백, 스페이스 허용하지 않음. ex) null(x), ""(x), "  "(x)
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
 *      {@literal @}Range(min= 100, max= 50000): 100에서 50000까지의 숫자만 유효성 검증에 성공한다.
 *     </li>
 * </ul>
 * <p>&nbsp;</p>
 * <h4>getter가 필요한 이유</h4>
 * 현재 버전은 CoffeeController에서 CoffeePostDto를 response body로 전달 하므로,
 * MappingJackson2HttpMessageConverter가 CoffeePostDto를 JSON 문자열로 변환할 때 getter를 사용합니다.
 */
public class CoffeePostDto {
    @NotBlank
    private String korName;

    @NotBlank
    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$",
            message = "커피명(영문)은 영문이어야 합니다(단어 사이 공백 한 칸 포함). 예:Cafe Latte")
    private String engName;

    @Range(min= 100, max= 50000)
    private int price;

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }

    public int getPrice() {
        return price;
    }
}
