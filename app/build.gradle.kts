plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.kisanregistry"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kisanregistry"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.fragment:fragment-ktx:1.8.4")
    implementation("androidx.appcompat:appcompat")
    implementation("androidx.constraintlayout:constraintlayout")
    implementation("com.google.android.material:material:1.10.0")


    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.3")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Gson
    implementation("com.google.code.gson:gson:2.11.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation(libs.androidx.activity)
    implementation(libs.androidx.gridlayout)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.junit.junit)
    kapt("androidx.room:room-compiler:2.6.1")

    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")

    implementation ("org.jsoup:jsoup:1.17.2") // For web scraping
    implementation ("androidx.recyclerview:recyclerview:1.3.2")// For RecyclerView
    // Existing dependencies (Room, Navigation, etc.)
    // Location
    implementation("com.google.android.gms:play-services-location:21.3.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")



}
