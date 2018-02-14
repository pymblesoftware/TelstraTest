package com.pymblesoftware.telstratest.network

/**
 * Created by regan on 14/2/18.
 */


object RepositoryProvider {
    fun provideRepository(): Repository {
        return Repository(ApiService.create())
    }
}

