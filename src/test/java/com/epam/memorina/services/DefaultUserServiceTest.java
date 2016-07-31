package com.epam.memorina.services;

import com.epam.memorina.assemblers.Assembler;
import com.epam.memorina.entities.UserEntity;
import com.epam.memorina.models.User;
import com.epam.memorina.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Solovev Anton
 * @since 31.07.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    @Mock
    private UserRepository mockedRepository;
    @Mock
    private Assembler<User, UserEntity> assembler;
    private DefaultUserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new DefaultUserService(mockedRepository, assembler);
    }

    @Test
    public void loadAllTest() throws Exception {
        UserEntity mockedEntity = mock(UserEntity.class);
        when(mockedRepository.findAll()).thenReturn(Arrays.asList(mockedEntity, mockedEntity));
        List<User> users = userService.loadAll();
        verify(mockedRepository, atLeastOnce()).findAll();
        assertThat(users.size(), is(2));
    }

    @Test
    public void loadByNameTest() throws Exception {
        UserEntity mockedEntity = mock(UserEntity.class);
        when(mockedRepository.findByUsername(anyString())).thenReturn(mockedEntity);
        String testUsername = "test-string";
        userService.loadByName(testUsername);
        verify(mockedRepository, atLeastOnce()).findByUsername(testUsername);
    }

    @Test
    public void validUserTest() throws Exception {
        String testPassword = "test-pass";
        User user1 = new User();
        user1.setUsername("test-name");
        user1.setPassword(testPassword);
        User spiedUser = spy(user1);

        when(assembler.newModel(any())).thenReturn(spiedUser);

        boolean isValidUser = userService.validUser(spiedUser);

//        assertTrue(isValidUser, is(TrueValue));
        verify(spiedUser, atLeastOnce()).getPassword();
    }


}