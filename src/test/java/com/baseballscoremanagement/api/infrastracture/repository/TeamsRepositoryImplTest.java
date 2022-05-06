package com.baseballscoremanagement.api.infrastracture.repository;

import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.infrastracture.library.TeamsMySqlLibrary;
import com.baseballscoremanagement.api.infrastracture.response.TeamResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class TeamsRepositoryImplTest {

  @Mock
  private TeamsMySqlLibrary teamsMySqlLibrary;

  @InjectMocks
  private TeamsRepositoryImpl teamsRepositoryImpl;

  @Nested
  class getTeamList {
    @Test
    void 取得した結果を正常に返すことができること() {
      final var expectTeam = new Team(1, "テストチーム");
      final var teamResponse = Flux.just(new TeamResponse(1, "テストチーム"));
      Mockito.doReturn(teamResponse).when(teamsMySqlLibrary).findTeams(1, 5);
      final var actualTeamList = teamsRepositoryImpl.getTeamList(1, 5);

      StepVerifier.create(actualTeamList).assertNext(team -> {
        Assertions.assertEquals(expectTeam.getId(), team.getId());
        Assertions.assertEquals(expectTeam.getName(), team.getName());
      }).expectNextCount(0).verifyComplete();
      Mockito.verify(teamsMySqlLibrary, Mockito.times(1)).findTeams(1, 5);
    }
  }
}
