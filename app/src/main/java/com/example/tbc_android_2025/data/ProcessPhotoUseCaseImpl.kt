package com.example.tbc_android_2025.data

import com.example.tbc_android_2025.domain.model.ProcessedPhoto
import com.example.tbc_android_2025.domain.use_case.local.ProcessPhotoUseCase
import javax.inject.Inject

class ProcessPhotoUseCaseImpl @Inject constructor(private val imageProcessor: ImageProcessor) :
    ProcessPhotoUseCase {

    override suspend operator fun invoke(uri: String): Result<ProcessedPhoto> =
        imageProcessor.process(uri = uri)

}
