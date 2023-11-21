package belajar.springwebmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreatePerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Chus")
                        .param("middleName", "nun")
                        .param("lastName", "Nidhom")
                        .param("email", "chusnunnidhom@gmail.com")
                        .param("phone", "0123456789")
                        .param("address.street", "jalan semrawut")
                        .param("address.city", "suroboyo")
                        .param("address.country", "indonesia")
                        .param("address.postalCode", "60198")
                        .param("hobbies[0]", "Reading")
                        .param("hobbies[1]", "gaming")
                        .param("hobbies[2]", "Coding")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "facebook.com/csn_nidhom")
                        .param("socialMedias[1].name", "Instagram")
                        .param("socialMedias[1].location", "instagram.com/csn_nidhom")

        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create person Chus nun Nidhom " +
                        "with email chusnunnidhom@gmail.com and phone 0123456789 " +
                        "with address jalan semrawut, suroboyo, indonesia, 60198"))
        );
    }

    @Test
    void testCreatePersonValidationError() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("middleName", "nun")
                        .param("lastName", "Nidhom")
                        .param("address.street", "jalan semrawut")
                        .param("address.city", "suroboyo")
                        .param("address.country", "indonesia")
                        .param("address.postalCode", "60198")
                        .param("hobbies[0]", "Reading")
                        .param("hobbies[1]", "gaming")
                        .param("hobbies[2]", "Coding")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "facebook.com/csn_nidhom")
                        .param("socialMedias[1].name", "Instagram")
                        .param("socialMedias[1].location", "instagram.com/csn_nidhom")

        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("You Send invalid data"))
        );
    }
}