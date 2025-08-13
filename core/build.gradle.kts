plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    alias(libs.plugins.compose.compiler)
}

@Suppress("UnstableApiUsage")
android {
    namespace = "id.herald.core"
    compileSdk = Versions.compile_sdk

    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    tasks.withType().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-opt-in=androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi",
            )
        }
    }
}

dependencies {

    // DEFAULT DEPENDENCIES
    api(libs.core.ktx)
    api(libs.lifecycle.ktx)

    // COMPOSE
    api(libs.compose.activity)
    api(platform(libs.compose.bom))
    api(libs.compose.ui)
    api(libs.compose.ui.graphics)
    api(libs.compose.ui.tooling.preview)
    api(libs.compose.material3)
    api(libs.compose.material3.window.size.class1)
    api(libs.compose.navigation)
    api(libs.compose.animation)

    // FIREBASE
    api(libs.firebase.database.ktx)

    // TESTING
    testImplementation(libs.junit.test)
    androidTestImplementation(libs.ext.junit.test)
    androidTestImplementation(libs.espresso.core.test)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4.test)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    // MOCKITO-KOTLIN
    testImplementation(libs.mockito.kotlin.test)

    // COROUTINES TEST
    testImplementation(libs.coroutines.test)

    // REMOTE
    api(libs.retrofit)
    api(libs.retrofit.converter.gson)
    api(libs.retrofit.adapter.rxjava2)
    api(libs.okhttp.logging.interceptor)

    // COIL
    api(libs.compose.coil)

    // Hilt
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
    api(libs.hilt.navigation.compose) {
        exclude("androidx.lifecycle", "lifecycle-viewmodel-ktx")
    }
    ksp(libs.hilt.compiler)

    // Room
    api(libs.room.runtime)
    ksp(libs.room.compiler)
    api(libs.room.ktx)

    // PAGER
    api(libs.accompanist.pager)
    api(libs.accompanist.pager.indicators)

    // System UI Controller
    api(libs.accompanist.systemuicontroller)
}