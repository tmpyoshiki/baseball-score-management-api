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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
      final List<Team> expectTeamList
          = IntStream.range(0,2)
          .mapToObj(i -> new Team(i, "テストチーム" + i))
          .collect(Collectors.toList());

      final Flux<TeamResponse> teamResponseList
          = Flux.range(0,2).map(i -> new TeamResponse(i, "テストチーム" + i));

      Mockito.doReturn(teamResponseList).when(teamsMySqlLibrary).findTeams(0, 2);
      final var actualTeamList = teamsRepositoryImpl.getTeamList(0, 2);

      StepVerifier
          .create(actualTeamList)
          .assertNext(team -> Assertions.assertEquals(expectTeamList.get(0), team))
          .assertNext(team -> Assertions.assertEquals(expectTeamList.get(1), team))
          .expectNextCount(0).verifyComplete();
      Mockito.verify(teamsMySqlLibrary, Mockito.times(1)).findTeams(0, 2);
    }
  }
}
