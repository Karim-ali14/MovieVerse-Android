package com.karimali.movieverse.features.onboarding.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.karimali.movieverse.core.common.dataStoreKeys.DataStoreKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnboardingDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
){
    val isOnboardingCompleted : Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }.map { preferences ->
            preferences[DataStoreKeys.IS_ONBOARDING_COMPLETED] ?: false
        }

    suspend fun setOnboardingCompleted(completed : Boolean){
        dataStore.edit { preferences ->
            preferences[DataStoreKeys.IS_ONBOARDING_COMPLETED] = completed
        }
    }
}