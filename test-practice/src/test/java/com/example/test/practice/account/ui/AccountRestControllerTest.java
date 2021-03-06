package com.example.test.practice.account.ui;

import com.example.test.practice.account.Account;
import com.example.test.practice.account.application.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * WebMvcTest와 MockBean을 이용한 웹 부분은 통합으로 로딩하고 나머지는 단위적으로 테스트
 *
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SuppressWarnings("WeakerAccess")
// TODO 1-1 @Web___Test(AccountRestController.class)
public class AccountRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    // TODO 1-2 : @M___B___ : hint Mocking
    AccountService accountService;

    @Test
    public void getAccount() throws Exception {
        // given
        Long accountId = random(Long.class);
        Account account = random(Account.class);
        given(accountService.getAccount(accountId)).willReturn(account);
        // when
        mockMvc.perform(get("/api/accounts/{0}", accountId)
                            .accept(MediaType.APPLICATION_JSON))
               // then
               //.andExpect(status().is__()) // FIXME 1-3 : 성공 응답
               .andExpect(jsonPath("$.accountId").value(account.getAccountId()))
               //.andExpect(jsonPath("$.____").value(account.getName()))  // FIXME 1-4 : 성공 응답
        ;
        // then2
        then(accountService).should(times(2)).getAccount(accountId);    // ?!
    }
}
