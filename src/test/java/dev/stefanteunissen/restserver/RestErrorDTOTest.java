package dev.stefanteunissen.restserver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RestErrorDTOTest {
    @Test
    void returnErrorMessagePizza() {
        RestErrorDTO restErrorDTO = new RestErrorDTO(1, "Pizza");
        assertEquals("Pizza", restErrorDTO.getErrorMessage());
    }

    @Test
    void returnErrorCode69() {
        RestErrorDTO restErrorDTO = new RestErrorDTO(69, "Pizza");
        assertEquals(69, restErrorDTO.getErrorCode());
    }
}
