package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        var task = Instancio.of(Task.class)
            .ignore(Select.field(Task::getId))
            .create();
        taskRepository.save(task);

        var request = get("/tasks/" + task.getId())
            .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isObject().containsEntry("title", task.getTitle());
    }

    @Test
    public void testCreate() throws Exception {
        var taskData = Instancio.of(Task.class)
            .ignore(Select.field(Task::getId))
            .create();

        var request = post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(taskData));

        var result = mockMvc.perform(request)
            .andExpect(status().isCreated())
            .andReturn();

        var body = result.getResponse().getContentAsString();

        var taskJson = om.readValue(body, Task.class);
        assertThat(taskJson.getTitle()).isEqualTo(taskData.getTitle());

        var taskDb = taskRepository.findById(taskJson.getId());
        assertThat(taskDb).isPresent();
    }

    @Test
    public void testUpdate() throws Exception {
        var task = Instancio.of(Task.class)
            .ignore(Select.field(Task::getId))
            .create();
        taskRepository.save(task);

        var updateData = Map.of("title", "Foo Bar");

        var request = put("/tasks/" + task.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(updateData));

        mockMvc.perform(request)
            .andExpect(status().isOk());

        var taskDb = taskRepository.findById(task.getId()).get();
        assertThat(taskDb.getTitle()).isEqualTo("Foo Bar");
    }

    @Test
    public void testDelete() throws Exception {
        var task = Instancio.of(Task.class)
            .ignore(Select.field(Task::getId))
            .create();
        taskRepository.save(task);

        var request = delete("/tasks/" + task.getId())
            .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
            .andExpect(status().isOk());

        var taskDb = taskRepository.findById(task.getId());
        assertThat(taskDb).isEmpty();
    }
    // END
}
