package exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Task;
import exercise.repository.TaskRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

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
    public void testOneTask() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setTitle("idk");
        taskRepository.save(task);
        var result = mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(a -> a.node("title").isEqualTo("idk"));


        mockMvc.perform(get("/tasks/9999"))
                .andExpect(status().is(404));
        taskRepository.deleteById(1L);

    }

    @Test
    public void testAdd() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setTitle("idk");
        var result = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated());

        assertThat(taskRepository.findById(1L).get().getTitle()).isEqualTo("idk");
        taskRepository.deleteById(1L);

    }

    @Test
    public void testUpdate() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setTitle("idk");
        taskRepository.save(task);
        Task task2 = new Task();
        task2.setId(1);
        task2.setTitle("not Idk");
        mockMvc.perform(put("/tasks/" + task.getId()
                ).contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task2)))
                .andExpect(status().isOk());


        assertThat(taskRepository.findById(1L).get().getTitle()).isEqualTo("not Idk");
        taskRepository.deleteById(1L);

    }

@Test
    public void testDelete() throws Exception {
    Task task = new Task();
    task.setId(1);
    task.setTitle("idk");
    taskRepository.save(task);
    mockMvc.perform(delete("/tasks/" + task.getId()))
            .andExpect(status().is(200));
    assertThat(taskRepository.findById(1L)).isEmpty();
}
    // END
}
