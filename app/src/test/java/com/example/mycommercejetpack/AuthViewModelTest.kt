package com.example.mycommercejetpack

import com.example.mycommercejetpack.repository.AuthRepository
import com.example.mycommercejetpack.viewmodels.AuthViewModel
import com.google.common.base.CharMatcher.any
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.capture

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {

    private lateinit var repository: AuthRepository
    private lateinit var viewModel: AuthViewModel

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        viewModel = AuthViewModel(repository)
    }

//    @Test
//    fun `login success updates state`() = runTest {
//        // Prepare a slot to capture the callback
//        val callbackSlot = slot<(Boolean, String?) -> Unit>()
//
//        // Mock repository behavior
//        every { repository.loginWithEmail(any(), any(), capture(callbackSlot)) } answers {
//            callbackSlot.captured(false, "Login failure: 123")
//        }
//
//        // Call ViewModel
//        viewModel.login("test@mail.com", "123456")
//
//        // Assert
//        val state = viewModel.authState.first()
//        Assert.assertTrue(state.isSuccess)
//
//    }

//    @Test
//    fun `login failure updates state`() = runTest {
//        val callbackSlot = slot<(Boolean, String?) -> Unit>()
//
//        every { repository.loginWithEmail(any(), any(), capture(callbackSlot)) } answers {
//            callbackSlot.captured(false, "Invalid credentials")
//        }
//
//        viewModel.login("wrong@mail.com", "badpass")
//
//        val state = viewModel.authState.first()
//        Assert.assertFalse(state.isSuccess)
//        Assert.assertEquals("Invalid credentials", state.message)
//    }

    @Test
    fun `register success updates state`() = runTest {
        val callbackSlot = slot<(Boolean, String?) -> Unit>()

        every { repository.registerWithEmail(any(), any(), capture(callbackSlot)) } answers {
            callbackSlot.captured(true, "User created: 456")
        }

        viewModel.register("new@mail.com", "123456")

        val state = viewModel.authState.first()
        Assert.assertTrue(state.isSuccess)
        Assert.assertEquals("User created: 456", state.message)
    }
}