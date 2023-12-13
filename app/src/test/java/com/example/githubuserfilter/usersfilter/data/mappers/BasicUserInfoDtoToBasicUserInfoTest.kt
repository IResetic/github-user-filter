package com.example.githubuserfilter.usersfilter.data.mappers

import com.example.githubuserfilter.usersfilter.data.api.model.fakeBasicUserInfoDtoOne
import com.example.githubuserfilter.usersfilter.domain.model.BasicUserInfo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class BasicUserInfoDtoToBasicUserInfoTest {
    private lateinit var sut: BasicUserInfoDtoToBasicUserInfo

    @Before
    fun init() {
        sut = BasicUserInfoDtoToBasicUserInfo()
    }

    @Test
    fun successfully_convert_basic_user_info_dto_to_domain() {
        // trigger action
        val actual = sut(fakeBasicUserInfoDtoOne)

        // check assertions
        val expected = BasicUserInfo(
            userId = fakeBasicUserInfoDtoOne.userId!!,
            username = fakeBasicUserInfoDtoOne.username!!,
            avatarUrl = fakeBasicUserInfoDtoOne.avatarUrl!!,
            type = fakeBasicUserInfoDtoOne.type!!,
            isSiteAdmin = fakeBasicUserInfoDtoOne.isSiteAdmin!!,
            score = fakeBasicUserInfoDtoOne.score!!
        )
        assertEquals(expected, actual)
    }

    @Test
    fun should_return_null_if_basic_user_info_dto_id_is_null() {
        // define test data
        val userIdNull = fakeBasicUserInfoDtoOne.copy(userId = null)

        // trigger action
        val actual = sut(userIdNull)

        // check assertions
        assertNull(actual)
    }
}
