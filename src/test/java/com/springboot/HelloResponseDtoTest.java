package com.springboot;

import com.springboot.web.dto.HelloResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        // assertj 테스트 검증 라이브러리
        // JUnit의 asserthat에 비해 CoreMather와 달리 추가적인 라이브러리가 필요하지 않고, 자동완성이 더 확실하게 지원된다.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
