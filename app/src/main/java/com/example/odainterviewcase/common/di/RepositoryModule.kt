/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.odainterviewcase.common.di

import android.content.res.AssetManager
import com.example.odainterviewcase.features.mixed.data.repository.MixedListRepositoryImpl
import com.example.odainterviewcase.features.product.data.repository.ProductListRepositoryImpl
import com.example.odainterviewcase.features.recipe.data.repository.RecipeListRepositoryImpl
import com.example.odainterviewcase.features.mixed.domain.MixedRepository
import com.example.odainterviewcase.features.product.domain.ProductRepository
import com.example.odainterviewcase.features.recipe.domain.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
  @Provides
  @ViewModelScoped
  fun provideMixedListRepository(
    assetManager: AssetManager,
  ): MixedRepository {
    return MixedListRepositoryImpl(assetManager )
  }

  @Provides
  @ViewModelScoped
  fun provideRecipeListRepository(
    assetManager: AssetManager,
  ): RecipeRepository {
    return RecipeListRepositoryImpl(assetManager)
  }

  @Provides
  @ViewModelScoped
  fun provideProductListRepository(
    assetManager: AssetManager,
  ): ProductRepository {
    return ProductListRepositoryImpl(assetManager )
  }

}


