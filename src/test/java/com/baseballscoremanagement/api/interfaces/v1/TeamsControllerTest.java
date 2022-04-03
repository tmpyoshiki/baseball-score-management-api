package com.baseballscoremanagement.api.interfaces.v1;

import com.baseballscoremanagement.api.application.TeamsService;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import com.baseballscoremanagement.api.interfaces.v1.response.TeamListResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class TeamsControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TeamsService teamsService;

  private final ObjectMapper mapper = new ObjectMapper();;

  @Nested
  class getTeamList {
    @Test
    void パラメータなしでリクエストしたときに取得した結果を返すことができること() throws Exception {
      final var teamList = List.of(new Team(1, "テストチーム"));
      final String expectResponseJson = mapper.writeValueAsString(new TeamListResponse(teamList));
      Mockito.doReturn(teamList).when(teamsService).getTeamList(TeamSort.DESC_GAMES, 1, 3);

      mockMvc.perform(MockMvcRequestBuilders.get("/v1/teams"))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(content().json(expectResponseJson));

      Mockito.verify(teamsService, Mockito.times(1)).getTeamList(TeamSort.DESC_GAMES, 1, 3);
    }
  }

}