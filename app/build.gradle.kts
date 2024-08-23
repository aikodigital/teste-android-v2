buildscript {
    dependencies {
        classpath(libs.secrets.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "local.defaults.properties"
    ignoreList.add("keyToIgnore")
    ignoreList.add("sdk.*")
}

android {
    namespace = "com.matreis.teste.sptrans"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.matreis.teste.sptrans"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_URL", "\"http://api.olhovivo.sptrans.com.br/v2.1/\"")
            buildConfigField("String", "API_TOKEN", "\"9003a3d066007886112ec5d2b3baa0325c3e2eca0951e18a6433a54583bc99ad\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_URL", "\"http://api.olhovivo.sptrans.com.br/v2.1/\"")
            buildConfigField("String", "API_TOKEN", "\"9003a3d066007886112ec5d2b3baa0325c3e2eca0951e18a6433a54583bc99ad\"")
        }
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
    // Annotation processor
    kapt (libs.androidx.lifecycle.compiler)
    kapt (libs.androidx.lifecycle.compiler)
    kapt (libs.hilt.compiler)

    // Dependencies
    implementation (libs.maps.ktx)
    implementation(libs.play.services.maps)
    implementation (libs.androidx.swiperefreshlayout)
    implementation (libs.threetenabp)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)
    implementation (libs.androidx.datastore.preferences)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.hilt.android)
    implementation (libs.okhttp)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.androidx.fragment)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

