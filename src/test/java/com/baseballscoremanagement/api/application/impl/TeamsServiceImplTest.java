package com.baseballscoremanagement.api.application.impl;

import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.repository.TeamsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
class TeamsServiceImplTest {

  @Mock
  private TeamsRepository teamsRepository;

  @InjectMocks
  private TeamsServiceImpl teamsServiceImpl;

  @Nested
  class getTeamList {
    @Test
    void 取得した結果を正常に返すことができること() {
      final List<Team> expectTeamList
          = IntStream.range(0,2)
          .mapToObj(i -> new Team(i, "テストチーム" + i))
          .collect(Collectors.toList());
      final Flux<Team> teamFlux = Flux.range(0,2).map(i -> new Team(i, "テストチーム" + i));

      Mockito.doReturn(teamFlux).when(teamsRepository).getTeamList(0, 2);
      final var actualTeamList = teamsServiceImpl.getTeamList(0, 2);
      Assertions.assertEquals(expectTeamList, actualTeamList);
      Mockito.verify(teamsRepository, Mockito.times(1)).getTeamList(0, 2);
    }
  }
}
