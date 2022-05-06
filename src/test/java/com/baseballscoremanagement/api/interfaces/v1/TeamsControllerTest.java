package com.baseballscoremanagement.api.interfaces.v1;

import com.baseballscoremanagement.api.application.GamesService;
import com.baseballscoremanagement.api.application.TeamsService;
import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.interfaces.v1.response.team.TeamListResponse;
import com.baseballscoremanagement.api.interfaces.v1.response.game.GameListResponse;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.baseballscoremanagement.api.helper.game.GameCreator.createGameList;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class TeamsControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TeamsService teamsService;

  @MockBean
  private GamesService gamesService;

  private final ObjectMapper mapper = new ObjectMapper();

  @Nested
  class getTeamList {
    @Test
    void パラメータなしでリクエストしたときに取得した結果を返すことができること() throws Exception {
      final List<Team> teamList = IntStream.range(0,3)
          .mapToObj(i -> new Team(i, "テストチーム" + i))
          .collect(Collectors.toList());
      final String expectResponseJson = mapper.writeValueAsString(new TeamListResponse(teamList));
      Mockito.doReturn(teamList).when(teamsService).getTeamList(0, 3);

      mockMvc.perform(MockMvcRequestBuilders.get("/v1/teams"))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(content().json(expectResponseJson));

      Mockito.verify(teamsService, Mockito.times(1)).getTeamList(0, 3);
    }
  }

  @Nested
  class getGameListByTeamId {
    @Test
    void パラメータなしでリクエストしたときに取得した結果を返すことができること() throws Exception {
      final List<Game> gameList = createGameList(3);
      final String expectResponseJson = mapper.writeValueAsString(new GameListResponse(gameList));
      Mockito.doReturn(gameList).when(gamesService).getGameListByTeamId(1, 0, 3);

      mockMvc.perform(MockMvcRequestBuilders.get("/v1/teams/{teamId}/games", 1))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(content().json(expectResponseJson));

      Mockito.verify(gamesService, Mockito.times(1)).getGameListByTeamId(1, 0, 3);
    }
  }

}