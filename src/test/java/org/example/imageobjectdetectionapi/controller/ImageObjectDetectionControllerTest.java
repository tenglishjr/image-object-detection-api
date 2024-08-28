package org.example.imageobjectdetectionapi.controller;

import org.example.imageobjectdetectionapi.entity.Image;
import org.example.imageobjectdetectionapi.entity.ImageTag;
import org.example.imageobjectdetectionapi.exception.ImageNotFoundException;
import org.example.imageobjectdetectionapi.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageObjectDetectionController.class)
@AutoConfigureMockMvc
public class ImageObjectDetectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    private Image image1, image2;

    @BeforeEach
    public void setUp() {
        image1 = new Image(
                1L,
                LocalDateTime.now(),
                "some/url",
                "some label",
                new ArrayList<>()
        );

        image2 = new Image(
                2L,
                LocalDateTime.now(),
                "some/other/url",
                "some other label",
                new ArrayList<>()
        );
    }

    @Test
    public void shouldReturnListOfImages() throws Exception {
        List<Image> images = List.of(image1, image2);
        when(imageService.findAll()).thenReturn(images);

        this.mockMvc.perform(get("/api/v1/images"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].dateUploaded", anything()))
                .andExpect(jsonPath("$[0].imageUrl", is("some/url")))
                .andExpect(jsonPath("$[0].label", is("some label")))
                .andExpect(jsonPath("$[0].imageTags").exists())
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].dateUploaded", anything()))
                .andExpect(jsonPath("$[1].imageUrl", is("some/other/url")))
                .andExpect(jsonPath("$[1].label", is("some other label")))
                .andExpect(jsonPath("$[1].imageTags").exists())
                .andReturn();
    }

    @Test
    public void shouldReturnOnlyImagesContainingCertainObjects() throws Exception {
        ImageTag imageTag1 = new ImageTag();
        ImageTag imageTag2 = new ImageTag();
        imageTag1.setTag("dog");
        imageTag2.setTag("cat");
        image1.getImageTags().add(imageTag1);
        image2.getImageTags().add(imageTag2);
        List<Image> expectedImage = List.of(image2);

        System.out.println("Image 1: " + image1.toString());
        System.out.println("Image 2: " + image2.toString());

        when(imageService.findAllWithObjects("\"cat\"")).thenReturn(expectedImage);

        this.mockMvc.perform(get("/api/v1/images?objects=\"cat\""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].dateUploaded", anything()))
                .andExpect(jsonPath("$[0].imageUrl", is("some/other/url")))
                .andExpect(jsonPath("$[0].label", is("some other label")))
                .andExpect(jsonPath("$[0].imageTags[0].tag", is("cat")))
                .andReturn();
    }

    @Test
    public void shouldReturnNoContentIfNoImagesFound() throws Exception {
        when(imageService.findAll()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/api/v1/images"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnImageWithId() throws Exception {
        when(imageService.findById(1L)).thenReturn(image1);

        this.mockMvc.perform(get("/api/v1/images/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.dateUploaded", anything()))
                .andExpect(jsonPath("$.imageUrl", is("some/url")))
                .andExpect(jsonPath("$.label", is("some label")))
                .andExpect(jsonPath("$.imageTags").exists());
    }

    @Test
    public void shouldReturnNotFoundIfImageWithIdDoesNotExist() throws Exception {
        when(imageService.findById(8L)).thenThrow(ImageNotFoundException.class);

        this.mockMvc.perform(get("/api/v1/images/{id}", 8))
                .andExpect(status().isNotFound());
    }

    // TODO: Tests for POST
}
